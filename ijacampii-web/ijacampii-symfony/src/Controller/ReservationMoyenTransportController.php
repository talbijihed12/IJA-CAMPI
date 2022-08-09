<?php

namespace App\Controller;

use App\Entity\ReservationMoyenTransport;
use App\Entity\MoyenTransport;
use App\Form\ReservationMoyenTransportType;
use Doctrine\ORM\EntityManagerInterface;
use phpDocumentor\Reflection\Types\This;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use App\Repository\ReservationMoyenTransportRepository;

/**
 * @Route("/reservation/moyen/transport")
 */
class ReservationMoyenTransportController extends AbstractController
{
    /**
     * @Route("/", name="app_reservation_moyen_transport_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $reservation_moyen_transports = $entityManager
            ->getRepository(ReservationMoyenTransport::class)
            ->findAll();

        return $this->render('reservation_moyen_transport/index.html.twig', [
            'reservation_moyen_transports' => $reservation_moyen_transports,
        ]);
    }

    /**
     * @Route("/", name="app_reservation_moyen_transport_indexfront", methods={"GET"})
     */
    public function indexfront(EntityManagerInterface $entityManager): Response
    {
        $reservationMoyenTransports = $entityManager
            ->getRepository(ReservationMoyenTransport::class)
            ->findAll();

        return $this->render('reservation_moyen_transport/indexfront.html.twig', [
            'reservation_moyen_transports' => $reservationMoyenTransports,
        ]);
    }

    /**
     * @Route("/indexJSON", name="app_moyen_transport_indexJSON")
     */
    public function indexJSON(NormalizerInterface $Normalizer): Response
    {
        $repository = $this->getDoctrine()->getRepository(ReservationMoyenTransport::class);
        $reservationMoyenTransports = $repository->findAll();

        $jsonContent = $Normalizer->normalize($reservationMoyenTransports, 'json',['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));
    }


    /**
     * @Route("/calendar", name="app_reservation_moyen_transport_calendar", methods={"GET"})
     */
    public function calendar(EntityManagerInterface $entityManager): Response
    {
        $reservationMoyenTransports = $entityManager
            ->getRepository(ReservationMoyenTransport::class)
            ->findAll();
            
            $reservations = [];

        foreach($reservationMoyenTransports as $reservationMoyenTransports){
            $reservations[] = [
                'id' => $reservationMoyenTransports->getIdReservation(),
                'start' => $reservationMoyenTransports->getDateDebut()->format('Y-m-d'),
                'end' => $reservationMoyenTransports->getDateFin()->format('Y-m-d'),
                'title' => $reservationMoyenTransports->getPlace(),
            ];
        }

        $data = json_encode($reservations);

        return $this->render('reservation_moyen_transport/calendar.html.twig', compact('data'));
    }

    

    /**
     * @Route("/affichage", name="app_reservation_moyen_transport_affichage", methods={"GET"})
     */
    public function affichage(EntityManagerInterface $entityManager): Response
    {
        $reservationMoyenTransports = $entityManager
            ->getRepository(ReservationMoyenTransport::class)
            ->findAll();

        return $this->render('reservation_moyen_transport/index.html.twig', [
            'reservation_moyen_transports' => $reservationMoyenTransports,
        ]);
    }

    /**
     * @Route("/new", name="app_reservation_moyen_transport_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $reservationMoyenTransport = new ReservationMoyenTransport(); //find
        $reservationMoyenTransport->setIdUser($this->getUser());
        $form = $this->createForm(ReservationMoyenTransportType::class, $reservationMoyenTransport);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($reservationMoyenTransport);
            $entityManager->flush();

            return $this->redirectToRoute('app_reservation_moyen_transport_indexfront', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reservation_moyen_transport/new.html.twig', [
            'moyen_transport' => $reservationMoyenTransport,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idReservation}", name="app_reservation_moyen_transport_show", methods={"GET"})
     */
    public function show(ReservationMoyenTransport $reservationMoyenTransport): Response
    {
        return $this->render('reservation_moyen_transport/show.html.twig', [
            'reservation_moyen_transport' => $reservationMoyenTransport,
        ]);
    }


    /**
     * @Route("/{idReservation}", name="app_reservation_moyen_transport_show_front", methods={"GET"})
     */
    public function showfront(ReservationMoyenTransport $reservationMoyenTransport): Response
    {
        return $this->render('reservation_moyen_transport/showfront.html.twig', [
            'reservation_moyen_transport' => $reservationMoyenTransport,
        ]);
    }


    /**
     * @Route("/{idReservation}/edit", name="app_reservation_moyen_transport_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, ReservationMoyenTransport $reservationMoyenTransport, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ReservationMoyenTransportType::class, $reservationMoyenTransport);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_reservation_moyen_transport_indexfront', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reservation_moyen_transport/edit.html.twig', [
            'reservation_moyen_transport' => $reservationMoyenTransport,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idReservation}", name="app_reservation_moyen_transport_delete", methods={"POST"})
     */
    public function delete(Request $request, ReservationMoyenTransport $reservationMoyenTransport, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reservationMoyenTransport->getIdReservation(), $request->request->get('_token'))) {
            $entityManager->remove($reservationMoyenTransport);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_reservation_moyen_transport_index', [], Response::HTTP_SEE_OTHER);
    }

    /**
     * @Route("/submit/{id}", name="app_reservation_moyen_transport_submit")
     */
    public function submit($id, Request $request, ReservationMoyenTransport $reservation, EntityManagerInterface $entityManager): Response
    {
        $reservation = new ReservationMoyenTransport(); //find

        
        $reservation->setIdTransport($this->getDoctrine()->getRepository(MoyenTransport::class)->find($id));

        $form = $this->createForm(ReservationMoyenTransportType::class, $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_reservation_moyen_transport_indexfront', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reservation_moyen_transport/edit.html.twig', [
            'reservation_moyen_transport' => $reservation,
            'form' => $form->createView(),
        ]);

    }

}
