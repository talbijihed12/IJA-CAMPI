<?php
namespace App\Controller\Mobile;

use App\Entity\ParticipementEvent;
use App\Repository\ParticipementEventRepository;
use App\Repository\UtilisateursRepository;
use App\Repository\EvenementRepository;
use DateTime;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/mobile/participementEvent")
 */
class ParticipementEventMobileController extends AbstractController
{
    /**
     * @Route("", methods={"GET"})
     */
    public function index(ParticipementEventRepository $participementEventRepository): Response
    {
        $participementEvents = $participementEventRepository->findAll();

        if ($participementEvents) {
            return new JsonResponse($participementEvents, 200);
        } else {
            return new JsonResponse([], 204);
        }
    }

    /**
     * @Route("/add", methods={"POST"})
     */
    public function add(Request $request, UtilisateursRepository $utilisateursRepository, EvenementRepository $evenementRepository): JsonResponse
    {
        $participementEvent = new ParticipementEvent();

        return $this->manage($participementEvent, $utilisateursRepository,  $evenementRepository,  $request, false);
    }

    /**
     * @Route("/edit", methods={"POST"})
     */
    public function edit(Request $request, ParticipementEventRepository $participementEventRepository, UtilisateursRepository $utilisateursRepository, EvenementRepository $evenementRepository): Response
    {
        $participementEvent = $participementEventRepository->find((int)$request->get("id"));

        if (!$participementEvent) {
            return new JsonResponse(null, 404);
        }

        return $this->manage($participementEvent, $utilisateursRepository, $evenementRepository, $request, true);
    }

    public function manage($participementEvent, $utilisateursRepository, $evenementRepository, $request, $isEdit): JsonResponse
    {   
        $utilisateurs = $utilisateursRepository->find((int)$request->get("utilisateurs"));
        if (!$utilisateurs) {
            return new JsonResponse("utilisateurs with id " . (int)$request->get("utilisateurs") . " does not exist", 203);
        }
        
        $evenement = $evenementRepository->find((int)$request->get("evenement"));
        if (!$evenement) {
            return new JsonResponse("evenement with id " . (int)$request->get("evenement") . " does not exist", 203);
        }
        
        
        $participementEvent->setUp(
            $utilisateurs,
            (int)$request->get("nbrParticipement"),
            $evenement
        );
        
        

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($participementEvent);
        $entityManager->flush();

        return new JsonResponse($participementEvent, 200);
    }

    /**
     * @Route("/delete", methods={"POST"})
     */
    public function delete(Request $request, EntityManagerInterface $entityManager, ParticipementEventRepository $participementEventRepository): JsonResponse
    {
        $participementEvent = $participementEventRepository->find((int)$request->get("id"));

        if (!$participementEvent) {
            return new JsonResponse(null, 200);
        }

        $entityManager->remove($participementEvent);
        $entityManager->flush();

        return new JsonResponse([], 200);
    }

    /**
     * @Route("/deleteAll", methods={"POST"})
     */
    public function deleteAll(EntityManagerInterface $entityManager, ParticipementEventRepository $participementEventRepository): Response
    {
        $participementEvents = $participementEventRepository->findAll();

        foreach ($participementEvents as $participementEvent) {
            $entityManager->remove($participementEvent);
            $entityManager->flush();
        }

        return new JsonResponse([], 200);
    }
    
}
