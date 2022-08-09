<?php
namespace App\Controller\Mobile;

use App\Entity\Utilisateurs;
use App\Repository\UtilisateursRepository;
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
 * @Route("/mobile/utilisateurs")
 */
class UtilisateursMobileController extends AbstractController
{
    /**
     * @Route("", methods={"GET"})
     */
    public function index(UtilisateursRepository $utilisateursRepository): Response
    {
        $utilisateurss = $utilisateursRepository->findAll();

        if ($utilisateurss) {
            return new JsonResponse($utilisateurss, 200);
        } else {
            return new JsonResponse([], 204);
        }
    }
}
