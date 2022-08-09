<?php
namespace App\Controller\Mobile;

use App\Entity\Evenement;
use App\Repository\EvenementRepository;
use App\Repository\HebergementRepository;
use App\Repository\UtilisateursRepository;
use App\Repository\EquipementRepository;
use App\Repository\MoyenTransportRepository;
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
 * @Route("/mobile/evenement")
 */
class EvenementMobileController extends AbstractController
{
    /**
     * @Route("", methods={"GET"})
     */
    public function index(EvenementRepository $evenementRepository): Response
    {
        $evenements = $evenementRepository->findAll();

        if ($evenements) {
            return new JsonResponse($evenements, 200);
        } else {
            return new JsonResponse([], 204);
        }
    }

    /**
     * @Route("/add", methods={"POST"})
     */
    public function add(Request $request, HebergementRepository $hebergementRepository, UtilisateursRepository $utilisateursRepository, EquipementRepository $equipementRepository, MoyenTransportRepository $moyenTransportRepository): JsonResponse
    {
        $evenement = new Evenement();

        return $this->manage($evenement, $hebergementRepository,  $utilisateursRepository,  $equipementRepository,  $moyenTransportRepository,  $request, false);
    }

    /**
     * @Route("/edit", methods={"POST"})
     */
    public function edit(Request $request, EvenementRepository $evenementRepository, HebergementRepository $hebergementRepository, UtilisateursRepository $utilisateursRepository, EquipementRepository $equipementRepository, MoyenTransportRepository $moyenTransportRepository): Response
    {
        $evenement = $evenementRepository->find((int)$request->get("id"));

        if (!$evenement) {
            return new JsonResponse(null, 404);
        }

        return $this->manage($evenement, $hebergementRepository, $utilisateursRepository, $equipementRepository, $moyenTransportRepository, $request, true);
    }

    public function manage($evenement, $hebergementRepository, $utilisateursRepository, $equipementRepository, $moyenTransportRepository, $request, $isEdit): JsonResponse
    {   
        $hebergement = $hebergementRepository->find((int)$request->get("hebergement"));
        if (!$hebergement) {
            return new JsonResponse("hebergement with id " . (int)$request->get("hebergement") . " does not exist", 203);
        }
        
        $utilisateurs = $utilisateursRepository->find((int)$request->get("utilisateurs"));
        if (!$utilisateurs) {
            return new JsonResponse("utilisateurs with id " . (int)$request->get("utilisateurs") . " does not exist", 203);
        }
        
        $equipement = $equipementRepository->find((int)$request->get("equipement"));
        if (!$equipement) {
            return new JsonResponse("equipement with id " . (int)$request->get("equipement") . " does not exist", 203);
        }
        
        $moyenTransport = $moyenTransportRepository->find((int)$request->get("moyenTransport"));
        if (!$moyenTransport) {
            return new JsonResponse("moyenTransport with id " . (int)$request->get("moyenTransport") . " does not exist", 203);
        }
        
        
        $evenement->setUp(
            $hebergement,
            $utilisateurs,
            $equipement,
            $moyenTransport,
            $request->get("nomEvent"),
            $request->get("description"),
            $request->get("dateDebut"),
            $request->get("dateFin"),
            (int)$request->get("nbrReservation"),
            (int)$request->get("prix"),
            $request->get("activite")
        );
        
        

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($evenement);
        $entityManager->flush();

        return new JsonResponse($evenement, 200);
    }

    /**
     * @Route("/delete", methods={"POST"})
     */
    public function delete(Request $request, EntityManagerInterface $entityManager, EvenementRepository $evenementRepository): JsonResponse
    {
        $evenement = $evenementRepository->find((int)$request->get("id"));

        if (!$evenement) {
            return new JsonResponse(null, 200);
        }

        $entityManager->remove($evenement);
        $entityManager->flush();

        return new JsonResponse([], 200);
    }

    /**
     * @Route("/deleteAll", methods={"POST"})
     */
    public function deleteAll(EntityManagerInterface $entityManager, EvenementRepository $evenementRepository): Response
    {
        $evenements = $evenementRepository->findAll();

        foreach ($evenements as $evenement) {
            $entityManager->remove($evenement);
            $entityManager->flush();
        }

        return new JsonResponse([], 200);
    }
    
}
