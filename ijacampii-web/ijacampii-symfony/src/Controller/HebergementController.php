<?php

namespace App\Controller;
use google\appengine\api\mail\Message;
use App\Entity\Hebergement;
use App\Form\HebergementType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use Knp\Component\Pager\PaginatorInterface;
/**
 * @Route("/hebergement")
 */
class HebergementController extends AbstractController
{
    /**
     * @Route("/", name="app_hebergement_index")
     */
    public function index(Request $request,EntityManagerInterface $entityManager, PaginatorInterface $paginator): Response
    {
        $hebergements = $entityManager
            ->getRepository(Hebergement::class)
            ->tri();
            $articles = $paginator->paginate(
                $hebergements, // Requête contenant les données à paginer (ici nos articles)
                $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
                5 // Nombre de résultats par page
            );
        return $this->render('hebergement/index.html.twig', [
            'articles' => $articles,
        ]);
    }

    /**
     * @Route("/new", name="app_hebergement_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager,\Swift_Mailer $mailer): Response
    {
        $hebergement = new Hebergement();
        $form = $this->createForm(HebergementType::class, $hebergement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($hebergement);
            $entityManager->flush();
           
            $message = (new \Swift_Message('Hello Email'))
            ->setFrom('seif_l@yahoo.fr')
            ->setTo('seif.labidi@esprit.tn')
             ->setBody(
           'kk'
        )

        // you can remove the following code if you don't define a text version for your emails
        ->addPart(
           
            
            'shutup'
        )
            
        
    ;
    $mailer->send($message);
    return $this->redirectToRoute('app_hebergement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('hebergement/new.html.twig', [
            'hebergement' => $hebergement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idH}", name="app_hebergement_show", methods={"GET"})
     */
    public function show(Hebergement $hebergement): Response
    {
        return $this->render('hebergement/show.html.twig', [
            'hebergement' => $hebergement,
        ]);
    }

    /**
     * @Route("/{idH}/edit", name="app_hebergement_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Hebergement $hebergement, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(HebergementType::class, $hebergement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_hebergement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('hebergement/edit.html.twig', [
            'hebergement' => $hebergement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idH}", name="app_hebergement_delete", methods={"POST"})
     */
    public function delete(Request $request, Hebergement $hebergement, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$hebergement->getIdH(), $request->request->get('_token'))) {
            $entityManager->remove($hebergement);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_hebergement_index', [], Response::HTTP_SEE_OTHER);
    }
    /**
     * @Route("/reserver", name="app_hebergement_reserver", methods={"GET", "POST"})
     */
    public function reserver(Hebergement $hebergement): Response
    {
        return $this->render('reservationsimple/new.html.twig', [
            'hebergement' => $hebergement,
        ]);
    }
}
