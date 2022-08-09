<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Entity\Equipement;
use App\Entity\LigneCommande;
use App\Form\LigneCommandeType;
use App\Repository\CommandeRepository;
use App\Repository\EquipementRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/ligne/commande",name="cart_")
 */
class LigneCommandeController extends AbstractController
{
    /**
     * @Route("/index", name="app_ligne_commande_index", methods={"GET"})
     */
      public function indexmain(EntityManagerInterface $entityManager): Response
      {
          $ligneCommandes = $entityManager
              ->getRepository(LigneCommande::class)
              ->findAll();

          return $this->render('ligne_commande/index.html.twig', [
              'ligne_commandes' => $ligneCommandes,
          ]);
      }

/*
      /**
       * @Route("/new", name="new", methods={"GET", "POST"})
       */
    /*  public function new(Request $request, EntityManagerInterface $entityManager): Response
      {
          $ligneCommande = new LigneCommande();
          $form = $this->createForm(LigneCommandeType::class, $ligneCommande);
          $form->handleRequest($request);

          if ($form->isSubmitted() && $form->isValid()) {
              $entityManager->persist($ligneCommande);
              $entityManager->flush();

              return $this->redirectToRoute('app_ligne_commande_index', [], Response::HTTP_SEE_OTHER);
          }

          return $this->render('ligne_commande/new.html.twig', [
              'ligne_commande' => $ligneCommande,
              'form' => $form->createView(),
          ]);
      }

      /**
       * @Route("/{id}", name="app_ligne_commande_show", methods={"GET"})
       */
    /*  public function show(LigneCommande $ligneCommande): Response
      {
          return $this->render('ligne_commande/show.html.twig', [
              'ligne_commande' => $ligneCommande,
          ]);
      }
*/
      /**
       * @Route("/get/{id}", name="show_commande")
       */
     public function showbycommande($id): Response
     {

         $ligneCommande = $this->getDoctrine()->getRepository(LigneCommande::class)->findByExampleField($id);

         return $this->render('ligne_commande/index.html.twig', [
             'ligne_commandes' => $ligneCommande,
         ]);
     }
/*
     /**
      * @Route("/{id}/edit", name="app_ligne_commande_edit", methods={"GET", "POST"})
      */
    /*  public function edit(Request $request, LigneCommande $ligneCommande, EntityManagerInterface $entityManager): Response
      {
          $form = $this->createForm(LigneCommandeType::class, $ligneCommande);
          $form->handleRequest($request);

          if ($form->isSubmitted() && $form->isValid()) {
              $entityManager->flush();

              return $this->redirectToRoute('app_ligne_commande_index', [], Response::HTTP_SEE_OTHER);
          }

          return $this->render('ligne_commande/edit.html.twig', [
              'ligne_commande' => $ligneCommande,
              'form' => $form->createView(),
          ]);
      }

      /**
       * @Route("/{id}", name="app_ligne_commande_delete", methods={"POST"})
       */
    /*  public function delete(Request $request, LigneCommande $ligneCommande, EntityManagerInterface $entityManager): Response
      {
          if ($this->isCsrfTokenValid('delete' . $ligneCommande->getId(), $request->request->get('_token'))) {
              $entityManager->remove($ligneCommande);
              $entityManager->flush();
          }

          return $this->redirectToRoute('app_ligne_commande_index', [], Response::HTTP_SEE_OTHER);
      }
  */
    /**
     * @Route("/", name="index")
     */

    public function index(SessionInterface $session, EquipementRepository $productsRepository)
    {

        $panier = $session->get("panier", []);

        // On "fabrique" les données
        $dataPanier = [];
        $total = 0;

        foreach ($panier as $id => $quantite) {
            $product = $productsRepository->find($id);
            $dataPanier[] = [
                "produit" => $product,
                "quantite" => $quantite
            ];
            $total += $product->getPrix() * $quantite;
        }

        return $this->render('ligne_commande/liste.html.twig', compact("dataPanier", "total"));
    }

    /**
     * @Route("/add/{id}", name="add")
     */
    public function add(Equipement $product, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);
        $id = $product->getId();

        if (!empty($panier[$id])) {
            $panier[$id]++;
        } else {
            $panier[$id] = 1;
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);

        return $this->redirectToRoute("cart_index");
    }

    /**
     * @Route("/remove/{id}", name="remove")
     */
    public function remove(Equipement $product, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);
        $id = $product->getId();

        if (!empty($panier[$id])) {
            if ($panier[$id] > 1) {
                $panier[$id]--;
            } else {
                unset($panier[$id]);
            }
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);

        return $this->redirectToRoute("cart_index");
    }

    /**
     * @Route("/supprimer/{id}", name="delete")
     */
    public function supprimer(Equipement $product, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);
        $id = $product->getId();

        if (!empty($panier[$id])) {
            unset($panier[$id]);
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);

        return $this->redirectToRoute("cart_index");
    }

    /**
     * @Route("/supprimer", name="delete_all")
     */
    public function suppall(SessionInterface $session)
    {
        $session->remove("panier");

        return $this->redirectToRoute("cart_index");
    }

    /**
     * @Route ("/submit", name="submit")
     */
    public function submitlc(SessionInterface $session, EntityManagerInterface $entityManager, Request $request,EquipementRepository $er,CommandeRepository $cr)
    {
        $total=0;
        $commande=new Commande();
        $commande->setIdUtilisateur($this->getUser());
        $commande->setAdresse("******************************");
        $commande->setDate(new \DateTime());
        $commande->setMontant($total);
        $commande->setReference(utf8_encode(random_bytes(20)));
        $entityManager->persist($commande);
        $entityManager->flush();
        $panier = $session->get("panier", []);
            foreach ($panier as $id => $quantite) {
                $product=$er->find($id);
                $LigneCommande = new LigneCommande();
                $LigneCommande->setIdEquipement($er->find($id));
                $LigneCommande->setQuantite($quantite);
                    $LigneCommande->setIdCommande($commande);
                /* $dataPanier[] = [
                "produit" => $product,
                "quantite" => $quantite
            ];*/
                $entityManager->persist($LigneCommande);
                $entityManager->flush();
                $lc[] = [
                    "id" => $LigneCommande->getId(),
                ];
                $total += $product->getPrix() * $quantite;
            }
        $commande->setMontant($total);
            $entityManager->flush();
            $ligneCommandes = $entityManager
            ->getRepository(LigneCommande::class)
            ->findAll();
        var_dump($commande);

        return $this->redirectToRoute('app_commande_submit', ['id'=>$commande->getId()], Response::HTTP_SEE_OTHER);

    }

}
