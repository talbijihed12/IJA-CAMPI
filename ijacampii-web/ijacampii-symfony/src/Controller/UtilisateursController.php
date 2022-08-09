<?php

namespace App\Controller;

use App\Entity\Utilisateurs;
use App\Form\UtilisateursType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use  Symfony\Component\Serializer\Normalizer\NormalizerInterface;


/**
 * @Route("/utilisateurs")
 */
class UtilisateursController extends AbstractController
{
    /**
     * @Route("/", name="app_utilisateurs_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $utilisateurs = $entityManager
            ->getRepository(Utilisateurs::class)
            ->findAll();

        return $this->render('utilisateurs/affichage.html.twig', [
            'utilisateurs' => $utilisateurs,
        ]);
    }
     /**
     * @Route("/front", name="app_utilisateurs_indexfront", methods={"GET"})
     */
    public function indexfront(EntityManagerInterface $entityManager): Response
    {
        $utilisateurs = $entityManager
            ->getRepository(Utilisateurs::class)
            ->findAll();

        return $this->render('utilisateurs/indexfront.html.twig', [
            'utilisateurs' => $utilisateurs,
        ]);
    }

    /**
     * @Route("/frontAcceuil", name="acceuil", methods={"GET"})
     */
    public function front(EntityManagerInterface $entityManager): Response
    {
        return $this->render('front.html.twig', [
        ]);
    }
    

    /**
     * @Route("/new", name="app_utilisateurs_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $utilisateur = new Utilisateurs();
        $form = $this->createForm(UtilisateursType::class, $utilisateur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($utilisateur);
            $entityManager->flush();

            return $this->redirectToRoute('app_utilisateurs_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('utilisateurs/new.html.twig', [
            'utilisateur' => $utilisateur,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/new", name="app_utilisateurs_new", methods={"GET", "POST"})
     */
    public function newfront(Request $request, EntityManagerInterface $entityManager): Response
    {
        $utilisateur = new Utilisateurs();
        $form = $this->createForm(UtilisateursType::class, $utilisateur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($utilisateur);
            $entityManager->flush();

            return $this->redirectToRoute('app_utilisateurs_indexfront', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('utilisateurs/new.html.twig', [
            'utilisateur' => $utilisateur,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/affichage", name="app_utilisateurs_affichage", methods={"GET"})
     */
    public function affichage(EntityManagerInterface $entityManager): Response
    {
        $utilisateurs = $entityManager
            ->getRepository(Utilisateurs::class)
            ->findAll();

        return $this->render('utilisateurs/affichage.html.twig', [
            'utilisateurs' => $utilisateurs,
        ]);
    }



    /**
     * @Route("/bloquer/{id}", name="user_bloque", methods={"GET","POST"})
     */
    public function bloquer(Request $request,  $id ): Response
    {

        $entityManager = $this->getDoctrine()->getManager();
        $reclamation = $this->getDoctrine()
            ->getRepository(Utilisateurs::class)
            ->find($id);
        $reclamation->setIsActive(0);

        $entityManager->flush();
        $this->getDoctrine()->getManager()->flush();
        return $this->redirectToRoute('app_utilisateurs_index');
    }


    /**
     * @Route("/debloquer/{id}", name="user_debloque", methods={"GET","POST"})
     */
    public function debloquer(Request $request,  $id ): Response
    {

        $entityManager = $this->getDoctrine()->getManager();
        $reclamation = $this->getDoctrine()
            ->getRepository(Utilisateurs::class)
            ->find($id);
        $reclamation->setIsActive(1);

        $entityManager->flush();
        $this->getDoctrine()->getManager()->flush();
        return $this->redirectToRoute('app_utilisateurs_index');
    }



    /**
     * @Route("/show", name="app_utilisateurs_show")
     */
    public function show(NormalizerInterface $Normalizer  ) 
    {
        $repository = $this->getDoctrine()->getRepository(Utilisateurs::class);
            $utilisateur =$repository->findAll();
            
$jsoncontent=$Normalizer->normalize($utilisateur,'json',['groups'=>'post:read']);
        
        return $this->render('utilisateurs/show.html.twig', [
            'utilisateur' => $utilisateur,
            'data'=>$jsoncontent
        ]);
        return new Response(json_encode($jsoncontent));
    }

    

        
/**
     * @Route("/edit", name="app_utilisateurs_edit", methods={"GET", "POST"})
     */
    public function editfront(Request $request, EntityManagerInterface $entityManager): Response
    {
        $repository = $this->getDoctrine()->getRepository(Utilisateurs::class);
        $utilisateur =$repository->find($this->getUser());
        $form = $this->createForm(UtilisateursType::class, $utilisateur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_evenement_indexfront', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('utilisateurs/edit.html.twig', [
            'utilisateur' => $utilisateur,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idUser}", name="app_utilisateurs_delete", methods={"POST"})
     */
    public function delete(Request $request, Utilisateurs $utilisateur, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$utilisateur->getIdUser(), $request->request->get('_token'))) {
            $entityManager->remove($utilisateur);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_utilisateurs_index', [], Response::HTTP_SEE_OTHER);
    }
    
    /**
     * @Route("/front/{idUser}", name="app_utilisateurs_deletefront", methods={"POST"})
     */
    
    public function deletefront(Request $request, Utilisateurs $utilisateur, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$utilisateur->getIdUser(), $request->request->get('_token'))) {
            $entityManager->remove($utilisateur);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_utilisateurs_indexfront', [], Response::HTTP_SEE_OTHER);
    }
}
