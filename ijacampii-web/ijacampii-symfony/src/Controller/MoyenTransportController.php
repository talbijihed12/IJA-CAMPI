<?php

namespace App\Controller;

use App\Entity\MoyenTransport;
use App\Form\MoyenTransport1Type;
use Doctrine\ORM\EntityManagerInterface;
use App\Entity\ReservationMoyenTransport;
use Knp\Component\Pager\PaginatorInterface;
use App\Repository\MoyenTransportRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\VarDumper\VarDumper;

/**
 * @Route("/moyen/transport")
 */
class MoyenTransportController extends AbstractController
{

     /**
     * @Route("/indexJSON", name="indexJSON")
     */
    public function indexJSON(NormalizerInterface $Normalizer): Response
    {
        $repository = $this->getDoctrine()->getRepository(MoyenTransport::class);
        $moyen_transports = $repository->findAll();

        $jsonContent = $Normalizer->normalize($moyen_transports, 'json',['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));
    }


    /**
     * @Route("/", name="app_moyen_transport_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager, Request $request, PaginatorInterface $paginator): Response
    {
        $donnees = $moyen_transports = $entityManager
            ->getRepository(MoyenTransport::class)
            ->findAll();

        $moyen_transports = $paginator->paginate(
            $donnees, 
            $request->query->getInt('page', 1),
            3
        );

        return $this->render('moyen_transport/index.html.twig', [
            'moyen_transports' => $moyen_transports,
        ]);
    }



   

    /**
     * @Route("/front", name="app_moyen_transport_cardsfront", methods={"GET"})
     */
    public function cardsfront(EntityManagerInterface $entityManager, Request $request, PaginatorInterface $paginator): Response
    {
        $donnees = $moyen_transports = $entityManager
            ->getRepository(MoyenTransport::class)
            ->findAll();

            $moyen_transports = $paginator->paginate(
                $donnees, // Requête contenant les données à paginer
                $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
                6 // Nombre de résultats par page
            );

        return $this->render('moyen_transport/cardsfront.html.twig', [
            'moyen_transports' => $moyen_transports,
        ]);
    }

    /**
     * @Route("/searchTransport", name="searchTransport")
     */
    public function searchTransport(Request $request,NormalizerInterface $Normalizer,MoyenTransportRepository $repository):Response
    {
        $requestString=$request->get('searchValue');
        $MoyenTransport = $repository->findByType($requestString);
        $jsonContent = $Normalizer->normalize($MoyenTransport, 'json',['Groups'=>'MoyenTransport:read']);
        $retour =json_encode($jsonContent);
        return new Response($retour);

    }


    /**
* @Route("/searchMoyen ", name="searchMoyen")
*/
public function searchMoyen(Request $request,NormalizerInterface $Normalizer,MoyenTransportRepository $repository)
{
//$repository = $this->getDoctrine()->getRepository(MoyenTransport::class);
$requestString=$request->get('searchValue');
$MoyenTransport = $repository->findByType($requestString);
$jsonContent = $Normalizer->normalize($MoyenTransport, 'json',['groups'=>'
MoyenTransport']);
var_dump($jsonContent );
$retour=json_encode($jsonContent);
return new Response($retour);
}



    /**
     * @Route("/affichage", name="app_moyen_transport_affichage", methods={"GET"})
     */
    public function affichage(EntityManagerInterface $entityManager, Request $request, PaginatorInterface $paginator): Response
    {
        $donnees = $moyen_transports = $entityManager
            ->getRepository(MoyenTransport::class)
            ->findAll();

        $moyen_transports = $paginator->paginate(
            $donnees, // Requête contenant les données à paginer
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            4 // Nombre de résultats par page
        );

        return $this->render('moyen_transport/affichage.html.twig', [
            'moyen_transports' => $moyen_transports,
        ]);
    }


    /**
     * @Route("/new", name="app_moyen_transport_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $moyenTransport = new MoyenTransport();
        $moyenTransport->setIdUser($this->getUser());
        $form = $this->createForm(MoyenTransport1Type::class, $moyenTransport);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($moyenTransport);
            $entityManager->flush();

            return $this->redirectToRoute('app_moyen_transport_affichage', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('moyen_transport/new.html.twig', [
            'moyen_transport' => $moyenTransport,
            'form' => $form->createView(),
        ]);
    }





    /**
     * @Route("/search/transport", name="searchTransport")
     */
    public function rechercherByType(EntityManagerInterface $entityManager, Request $request,MoyenTransportRepository $repository):Response
    {
        $entityManager=$this->getDoctrine()->getManager();
        $moyen_transports=$entityManager->getRepository(MoyenTransport::class)
            ->findAll();

        if($request->isMethod("POST"))
        {
            $type = $request->get('type');
            $moyen_transports = $entityManager->getRepository(MoyenTransport::class)->findBy(array('type'=>$type));
        }

            return $this->render('moyen_transport/cardsfront.html.twig', array('moyen_transports'=>$moyen_transports)); 
    }

    /**
     * @Route("/{idTransport}", name="app_moyen_transport_show", methods={"GET"})
     */
    public function show(MoyenTransport $moyenTransport): Response
    {
        return $this->render('moyen_transport/show.html.twig', [
            'moyen_transport' => $moyenTransport,
        ]);
    }

    /**
     * @Route("/show/front/{idTransport}", name="app_moyen_transport_show_front", methods={"GET"})
     */
    public function showfront(MoyenTransport $moyenTransport): Response
    {
        return $this->render('moyen_transport/showfront.html.twig', [
            'moyen_transport' => $moyenTransport,
        ]);
    }

    /**
     * @Route("/{idTransport}/edit", name="app_moyen_transport_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, MoyenTransport $moyenTransport, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(MoyenTransport1Type::class, $moyenTransport);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_moyen_transport_affichage', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('moyen_transport/edit.html.twig', [
            'moyen_transport' => $moyenTransport,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idTransport}", name="app_moyen_transport_delete", methods={"POST"})
     */
    public function delete(Request $request, MoyenTransport $moyenTransport, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$moyenTransport->getIdTransport(), $request->request->get('_token'))) {
            $entityManager->remove($moyenTransport);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_moyen_transport_affichage', [], Response::HTTP_SEE_OTHER);
    }

    /**
     * @Route("/submit/{idTransport}", name="app_moyen_transport_submit", methods={"POST"})
     */
    public function submit($idTransport, Request $request, ReservationMoyenTransport $reservationMoyenTransport, EntityManagerInterface $entityManager): Response
    {
        $reservation = new ReservationMoyenTransport();
        $reservation->setIdTransport($idTransport);
        $reservation->setDateDebut(new \DateTime());
        $reservation->setDateFin(new \DateTime());
        $reservation->setPlace("beja");
        $reservation->setIdUser($this->getDoctrine()->getRepository(Utilisateurs::class)->find(1));

            $entityManager->persist($reservationMoyenTransport);
            $entityManager->flush();

            return $this->redirectToRoute('app_reservation_moyen_transport_submit', ['id'=>$reservation->getIdReservation()], Response::HTTP_SEE_OTHER);

    }

    

}
