<?php

namespace App\Controller;

use App\Entity\Reservationsimple;
use App\Form\ReservationsimpleType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Hebergement; 
use Knp\Component\Pager\PaginatorInterface;
/**
 * @Route("/reservationsimple")
 */
class ReservationsimpleController extends AbstractController
{
    /**
     * @Route("/", name="app_reservationsimple_index", methods={"GET"})
     */
    public function index(Request $request,EntityManagerInterface $entityManager, PaginatorInterface $paginator): Response
    {
        $rs = $entityManager
            ->getRepository(Reservationsimple::class)
            ->findAll();
            $reservationsimples = $paginator->paginate(
                $rs, // Requête contenant les données à paginer (ici nos articles)
                $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
                9 // Nombre de résultats par page
            );
        return $this->render('reservationsimple/index.html.twig', [
            'reservationsimples' => $reservationsimples,
        ]);
    }

    /**
     * @Route("/{idH}/new", name="app_reservationsimple_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager,Hebergement $hebergement): Response
    {
        $reservationsimple = new Reservationsimple();
        
        $reservationsimple->setIdh($hebergement);
        $reservationsimple->setNamehebergement($hebergement->getName());
        $form = $this->createForm(ReservationsimpleType::class, $reservationsimple);
        $form->handleRequest($request);
        
        if ($form->isSubmitted() && $form->isValid()) {
                        $d2 =$reservationsimple->getDatefin();
                        $d1 =$reservationsimple->getDatedebut();
                        $r1 = $this->getDoctrine()
                                ->getRepository(Reservationsimple::class)
                                
                                ->AfficherReservationParDateDebut($d1,$d2);
                        $r2 = $this->getDoctrine()
                                ->getRepository(Reservationsimple::class)
                                
                                ->AfficherReservationParDatefin($d1,$d2);
                        if ($reservationsimple->getDatefin() < $reservationsimple->getDatedebut()) {
                        $this->addFlash('success', 'La date de début ne peut pas etre après la date de fin');
                        return $this->render('reservationsimple/new.html.twig', [
                            'reservationsimple' => $reservationsimple,
                            'form' => $form->createView(),
                        ]);}
                        else if ($r1 || $r2)
                        { 
                            $this->addFlash('success', 'mchet');
                            return $this->render('reservationsimple/new.html.twig', [
                                'reservationsimple' => $reservationsimple,
                                'form' => $form->createView(),
                            ]);
                         }
                         else {
                            $this->addFlash('success', 'Article reservé');
                            $entityManager->persist($reservationsimple);
                            $entityManager->flush();
                            return $this->redirectToRoute('app_reservationsimple_index', ['hebergement' => $hebergement,], Response::HTTP_SEE_OTHER);
                             }
                         
    }
        else {
        return $this->render('reservationsimple/new.html.twig', [
            'reservationsimple' => $reservationsimple,
            'form' => $form->createView(),
        ]);
    }
}

    /**
     * @Route("/{idRs}", name="app_reservationsimple_show", methods={"GET"})
     */
    public function show(Reservationsimple $reservationsimple): Response
    {
        return $this->render('reservationsimple/show.html.twig', [
            'reservationsimple' => $reservationsimple,
        ]);
    }

    /**
     * @Route("/{idRs}/edit", name="app_reservationsimple_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Reservationsimple $reservationsimple, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ReservationsimpleType::class, $reservationsimple);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_reservationsimple_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reservationsimple/edit.html.twig', [
            'reservationsimple' => $reservationsimple,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idRs}", name="app_reservationsimple_delete", methods={"POST"})
     */
    public function delete(Request $request, Reservationsimple $reservationsimple, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reservationsimple->getIdRs(), $request->request->get('_token'))) {
            $entityManager->remove($reservationsimple);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_reservationsimple_index', [], Response::HTTP_SEE_OTHER);
    }
    /**
     * @Route("/reserver", name="app_reservationsimple_reserver", methods={"GET", "POST"})
     */
    public function reserver(Request $request, EntityManagerInterface $entityManager): Response
    {
        $reservationsimple = new Reservationsimple();
        $form = $this->createForm(ReservationsimpleType::class, $reservationsimple);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($reservationsimple);
            $entityManager->flush();

            return $this->redirectToRoute('app_reservationsimple_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reservationsimple/new.html.twig', [
            'reservationsimple' => $reservationsimple,
            'form' => $form->createView(),
        ]);
    }
}
