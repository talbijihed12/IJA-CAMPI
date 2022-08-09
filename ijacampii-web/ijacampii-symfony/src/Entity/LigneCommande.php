<?php

namespace App\Entity;

use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * LigneCommande
 *
 * @ORM\Table(name="ligne_commande", indexes={@ORM\Index(name="id_commande", columns={"id_commande"}), @ORM\Index(name="id_equipement", columns={"id_equipement"})})
 * @ORM\Entity(repositoryClass="App\Repository\LigneCommandeRepository")
 */
class LigneCommande
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var int
     * @Assert\Type(message="Entrez quantite valide", type="integer")
     * @Assert\NotBlank(message="ce champs est obligatoire")
     * @Assert\GreaterThanOrEqual(message="la quantite doit être supérieure a zéro!", value = 0)
     * @ORM\Column(name="quantite", type="integer", nullable=false)
     */
    private $quantite;

    /**
     * @var \Equipement
     *
     * @ORM\ManyToOne(targetEntity="Equipement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_equipement", referencedColumnName="id")
     * })
     */
    private $idEquipement;

    /**
     * @var \Commande
     *
     * @ORM\ManyToOne(targetEntity="Commande")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_commande", referencedColumnName="id",onDelete="CASCADE")
     * })
     */
    private $idCommande;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getQuantite(): ?int
    {
        return $this->quantite;
    }

    public function setQuantite(int $quantite): self
    {
        $this->quantite = $quantite;

        return $this;
    }

    public function getIdEquipement(): ?Equipement
    {
        return $this->idEquipement;
    }

    public function setIdEquipement(?Equipement $idEquipement): self
    {
        $this->idEquipement = $idEquipement;

        return $this;
    }

    public function getIdCommande(): ?Commande
    {
        return $this->idCommande;
    }

    public function setIdCommande(?Commande $idCommande): self
    {
        $this->idCommande = $idCommande;

        return $this;
    }


}
