<?php
namespace App\Controller\Mobile;

use App\Entity\Hebergement;
use App\Repository\HebergementRepository;
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
 * @Route("/mobile/hebergement")
 */
class HebergementMobileController extends AbstractController
{
    /**
     * @Route("", methods={"GET"})
     */
    public function index(HebergementRepository $hebergementRepository): Response
    {
        $hebergements = $hebergementRepository->findAll();

        if ($hebergements) {
            return new JsonResponse($hebergements, 200);
        } else {
            return new JsonResponse([], 204);
        }
    }
    
}
