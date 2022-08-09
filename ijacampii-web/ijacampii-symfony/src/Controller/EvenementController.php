<?php

namespace App\Controller;

use App\Entity\Evenement;
use App\Entity\ParticipementEvent;
use App\Form\EvenementType;
use App\Services\CodeQr;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use phpDocumentor\Reflection\Types\This;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/evenement")
 */
class EvenementController extends AbstractController
{
    /**
     * @Route("/event", name="app_evenement_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager,Request $request, PaginatorInterface $paginator): Response
    {
        $evenements = $entityManager->getRepository(Evenement::class)
            ->findAll();
        $donnees = $this->getDoctrine() ->getRepository(Evenement::class)
            ->findAll();
        $articles = $paginator->paginate(
            $donnees, // Requête contenant les données à paginer (ici nos articles)
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            3 // Nombre de résultats par page
        );

        return $this->render('evenement/index.html.twig', [
            'evenements' => $evenements,
            'evenements' => $articles,
        ]);
    }
    /**
     * @Route("/eventindex", name="app_evenement_indexfront", methods={"GET"})
     */
    public function indexfront(EntityManagerInterface $entityManager,Request $request, PaginatorInterface $paginator): Response
    {
      $repo =$this->getDoctrine()->getRepository(Evenement::class);
      $evenement=$repo->findAll();
        $donnees = $this->getDoctrine() ->getRepository(Evenement::class)
            ->findAll();
        $articles = $paginator->paginate(
            $donnees, // Requête contenant les données à paginer (ici nos articles)
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            3 // Nombre de résultats par page
        );
        return $this->render('evenement/indexfront.html.twig', [
            'evenement' => $evenement,
            'evenements' => $articles,
        ]);

    }
    /**
     * @Route("/home", name="app_evenement_home", methods={"GET"})
     */
    public function home(): Response
    {

        return $this->render('evenement/home.html.twig', [

        ]);

    }


    /**
     * @Route("/new", name="app_evenement_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager, CodeQr $qrcodeService): Response
    {
        $evenement = new Evenement();
        $evenement->setIdUser($this->getUser());
        $form = $this->createForm(EvenementType::class, $evenement);
        $form->handleRequest($request);
        $data = $form->getData();
        $qrCode= $qrcodeService->qrcode($data->getNomEvent());
        $badWords = $this->filterwords($evenement->getDescription() . ' ' . $evenement->getDescription());
        if (strpos($badWords, '**') !== false) {
            $this->addFlash('info', 'Faites attention a ce que vous tapez ! un peu de respect !!');
        } else {

            if ($form->isSubmitted() && $form->isValid()) {
                $entityManager->persist($evenement);
                $entityManager->flush();
                $this->addFlash('info','Added with success');



                return $this->redirectToRoute('app_evenement_index', [], Response::HTTP_SEE_OTHER);
            }
        }
        return $this->render('evenement/new.html.twig', [
            'evenement' => $evenement,
            'form' => $form->createView(),
            'qrCode'=>$qrCode,
        ]);
    }
    /**
     * @Route("/newFront", name="app_evenement_new_front", methods={"GET", "POST"})
     */
    public function newfront(Request $request ): Response
    {
        $evenement = new Evenement();
        $evenement->setIdUser($this->getUser());
        $form = $this->createForm(EvenementType::class, $evenement);
        $form->handleRequest($request);
        $badWords = $this->filterwords($evenement->getDescription() . ' ' . $evenement->getDescription());
        if (strpos($badWords, '**') !== false) {
            $this->addFlash('info', 'Faites attention a ce que vous tapez ! un peu de respect !!');
        } else {

            if ($form->isSubmitted() && $form->isValid()) {
                $em = $this->getDoctrine()->getManager();
                $em->persist($evenement);
                $em->flush();
                $this->addFlash('info','Added with success');

                return $this->redirectToRoute('app_evenement_indexfront', [], Response::HTTP_SEE_OTHER);
            }
        }

        return $this->render('evenement/new_front.html.twig', [
            'evenement' => $evenement,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/{idEvent}", name="app_evenement_show", methods={"GET"})
     */
    public function show(Evenement $evenement): Response
    {
        return $this->render('evenement/show.html.twig', [
            'evenement' => $evenement,
        ]);
    }

    /**
     * @Route("/{idEvent}/edit", name="app_evenement_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Evenement $evenement, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(EvenementType::class, $evenement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();
            $this->addFlash('info','Edit successfully');

            return $this->redirectToRoute('app_evenement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('evenement/edit.html.twig', [
            'evenement' => $evenement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idEvent}", name="app_evenement_delete", methods={"POST"})
     */
    public function delete(Request $request, Evenement $evenement, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$evenement->getIdEvent(), $request->request->get('_token'))) {
            $entityManager->remove($evenement);
            $entityManager->flush();
            $this->addFlash('info','Deleted with success');
        }

        return $this->redirectToRoute('app_evenement_index', [], Response::HTTP_SEE_OTHER);
    }
    function filterwords($text){
        $delimiter = ',';
        $enclosure = '"';
        $header = NULL;
        $data = array();

        if (($handle = fopen("https://docs.google.com/spreadsheets/d/10P3ihV-l2Hz9Jm1Cprp8S7mTKqYsOZWxzaNOC8ij72M/export?format=csv", 'r')) !== FALSE) {

            while (($row = fgetcsv($handle, 0, $delimiter, $enclosure)) !== FALSE) {

                if(!$header) {
                    $header = $row;

                } else {
                    array_push($data,$row);
                }
            }
            fclose($handle);
        }
#dd($data[300][0]);
        $filterWords = array('badword');
        foreach($data as $s)
        {
            array_push($filterWords,$s[0]);
        }

#dd($filterWords);
        $filterCount = sizeof($filterWords);
        for ($i = 0; $i < $filterCount; $i++) {
            $text = preg_replace_callback('/\b' . $filterWords[$i] . '\b/i', function($matches){return str_repeat('*', strlen($matches[0]));}, $text);
        }
        return $text;
    }

}
