<?php

namespace App\Controller;

use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use App\Entity\Equipement;
use App\Form\EquipementType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;
/**
 * @Route("/equipement")
 */
class EquipementController extends AbstractController
{
    /**
     * @Route("/", name="app_equipement_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager, Request $request, PaginatorInterface $paginator): Response
    {
        $donnees = $entityManager
            ->getRepository(Equipement::class)
            ->findAll();
        $equipements = $paginator->paginate(
            $donnees, // Requête contenant les données à paginer
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            6 // Nombre de résultats par page
        );
        return $this->render('equipement/index.html.twig', [
            'equipements' => $equipements,
        ]);
    }/**
     * @Route("/back", name="app_equipement_index_back", methods={"GET"})
     */
    public function indexback(EntityManagerInterface $entityManager): Response
    {
        $equipements = $entityManager
            ->getRepository(Equipement::class)
            ->findAll();

        return $this->render('equipement/back.html.twig', [
            'equipements' => $equipements,
        ]);
    }


    /**
     * @Route("/new", name="app_equipement_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $equipement = new Equipement();
        $form = $this->createForm(EquipementType::class, $equipement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($equipement);
            $entityManager->flush();

            return $this->redirectToRoute('app_equipement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('equipement/new.html.twig', [
            'equipement' => $equipement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_equipement_show", methods={"GET"})
     */
    public function show(Equipement $equipement): Response
    {
        return $this->render('equipement/show.html.twig', [
            'equipement' => $equipement,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_equipement_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Equipement $equipement, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(EquipementType::class, $equipement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_equipement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('equipement/edit.html.twig', [
            'equipement' => $equipement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_equipement_delete", methods={"POST"})
     */
    public function delete(Request $request, Equipement $equipement, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$equipement->getId(), $request->request->get('_token'))) {
            $entityManager->remove($equipement);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_equipement_index', [], Response::HTTP_SEE_OTHER);
    }
    /**
     * @Route("/{id}/delete", name="app_equipement_del")
     */
    public function del(EntityManagerInterface $entityManager, $id): Response
    {
        $equipements = $entityManager
            ->getRepository(Equipement::class)
            ->find($id);

        $entityManager->remove($equipements);
        $entityManager->flush();
        return $this->redirectToRoute('app_equipement_index_back', [], Response::HTTP_SEE_OTHER);
    }
    /**
     * @Route("/json/new", name="app_equipement_nj", methods={"GET", "POST"})
     */
    public function newJ(Request $request, EntityManagerInterface $entityManager,NormalizerInterface $normalizer): Response
    {
        $equipement = new Equipement();
        // $equipement->setIdUtilisateur("1");
        $equipement->setNom($request->get('nom'));
        $equipement->setCategorie($request->get('categorie'));
        $equipement->setDescription($request->get('description'));
        $equipement->setMarque($request->get('marque'));
        $equipement->setPhoto($request->get('photo'));
        $equipement->setPrix($request->get('prix'));
        $entityManager->persist($equipement);
        $entityManager->flush();
        $jsonContent=$normalizer->normalize($equipement,'json',['groups'=>'post::read']);
        return new Response(json_encode($jsonContent));


    }
}

