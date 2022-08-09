<?php
namespace App\Controller\Mobile;

use App\Entity\Equipement;
use App\Repository\EquipementRepository;
use DateTime;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/mobile/equipement")
 */
class EquipementMobileController extends AbstractController
{
    /**
     * @Route("", methods={"GET"})
     */
    public function index(EquipementRepository $equipementRepository): Response
    {
        $equipements = $equipementRepository->findAll();

        if ($equipements) {
            return new JsonResponse($equipements, 200);
        } else {
            return new JsonResponse([], 204);
        }
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
