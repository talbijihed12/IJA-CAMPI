<?php
namespace App\Controller\Mobile;

use App\Entity\MoyenTransport;
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

use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
/**
 * @Route("/mobile/moyenTransport")
 */
class MoyenTransportMobileController extends AbstractController
{
    /**
     * @Route("", methods={"GET"})
     */
    public function index(MoyenTransportRepository $moyenTransportRepository): Response
    {
        $moyenTransports = $moyenTransportRepository->findAll();

        if ($moyenTransports) {
            return new JsonResponse($moyenTransports, 200);
        } else {
            return new JsonResponse([], 204);
        }
    }
    /**
     * @Route("/newJson", name="app_moyen_transport_newJson", methods={"GET", "POST"})
     */
    public function newJSON(Request $request, EntityManagerInterface $entityManager,NormalizerInterface $normalizer): Response
    {
        $MoyenTransport = new MoyenTransport();
        $MoyenTransport->setType($request->get('type'));
        $MoyenTransport->setMatricule($request->get('matricule'));
        $MoyenTransport->setMarque($request->get('marque'));
        $MoyenTransport->setNbrPlace($request->get('nbr_place'));
        $MoyenTransport->setFrais($request->get('frais'));
        $entityManager->persist($MoyenTransport);
        $entityManager->flush();
        $jsonContent=$normalizer->normalize($MoyenTransport,'json',['groups'=>'post::read']);
        return new Response(json_encode($jsonContent));

    }
}
