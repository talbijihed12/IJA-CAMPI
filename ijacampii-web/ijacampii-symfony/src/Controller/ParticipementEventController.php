<?php

namespace App\Controller;

use App\Entity\Evenement;
use Knp\Component\Pager\PaginatorInterface;
use App\Entity\ParticipementEvent;
use App\Entity\Utilisateurs;
use App\Form\ParticipementEventType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/participement/event")
 */
class ParticipementEventController extends AbstractController
{
    /**
     * @Route("/", name="app_participement_event_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager,Request $request, PaginatorInterface $paginator): Response
    {
        $evenements = $entityManager->getRepository(Evenement::class)->findAll();
        $utilisateurs = $entityManager->getRepository(Utilisateurs::class)->findAll();
        $donnees = $this->getDoctrine() ->getRepository(ParticipementEvent::class)
            ->findAll();
        $articles = $paginator->paginate(
            $donnees, // Requête contenant les données à paginer (ici nos articles)
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            1 // Nombre de résultats par page
        );

        return $this->render('participement_event/index.html.twig', [
            'participement_events' => $articles,
            'evenements'=>$evenements,
            'utilisateurs'=>$utilisateurs,
        ]);
    }

    /**
     * @Route("/new", name="app_participement_event_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $participementEvent = new ParticipementEvent();
        $evenement =new Evenement();
        $participementEvent->setIdUser($this->getUser());
        $form = $this->createForm(ParticipementEventType::class, $participementEvent);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($participementEvent);
            $entityManager->flush();
            $this->addFlash('info','Added with success');

            return $this->redirectToRoute('app_evenement_indexfront', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('participement_event/new.html.twig', [
            'participement_event' => $participementEvent,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idParticipement}", name="app_participement_event_show", methods={"GET"})
     */
    public function show(ParticipementEvent $participementEvent): Response
    {

        return $this->render('participement_event/show.html.twig', [
            'participement_event' => $participementEvent,

        ]);
    }

    /**
     * @Route("/{idParticipement}/edit", name="app_participement_event_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, ParticipementEvent $participementEvent, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ParticipementEventType::class, $participementEvent);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();
            $this->addFlash('info','Edit successfully');

            return $this->redirectToRoute('app_participement_event_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('participement_event/edit.html.twig', [
            'participement_event' => $participementEvent,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idParticipement}", name="app_participement_event_delete", methods={"POST"})
     */
    public function delete(Request $request, ParticipementEvent $participementEvent, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$participementEvent->getIdParticipement(), $request->request->get('_token'))) {
            $entityManager->remove($participementEvent);
            $entityManager->flush();
            $this->addFlash('info','Deleted Successfully');
        }

        return $this->redirectToRoute('app_participement_event_index', [], Response::HTTP_SEE_OTHER);
    }
}
