<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use JsonSerializable;

/**
 * Hebergement
 *
 * @ORM\Table(name="hebergement", indexes={@ORM\Index(name="id_user", columns={"id_user"})})
 * @ORM\Entity(repositoryClass="App\Repository\HebergementRepository")
 */
class Hebergement implements JsonSerializable
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_h", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idH;
    public function __toString() :string {
        return $this->idH;


    }
    

    /**
     * @var string
     * @Assert\NotNull
     * @Assert\NotBlank
     * @ORM\Column(name="name", type="string", length=100, nullable=false)
     */
    private $name;

    /**
     * @var string
     * @Assert\NotNull
     * @Assert\NotBlank
     * @ORM\Column(name="ville", type="string", length=20, nullable=false)
     */
    private $ville;

    /**
     * @var string
     * @Assert\NotNull
     * @Assert\NotBlank
     * @ORM\Column(name="categorie", type="string", length=20, nullable=false)
     */
    private $categorie;

    /**
     * @var int
     * @Assert\NotNull
     * @Assert\NotBlank
     * @ORM\Column(name="capacite", type="integer", nullable=false)
     */
    private $capacite;

    /**
     * @var bool
     * @Assert\NotNull
     * @Assert\NotBlank
     * @ORM\Column(name="disponibilite", type="boolean", nullable=false)
     */
    private $disponibilite;

    /**
     * @var int
     *
     * @ORM\Column(name="prix", type="integer", nullable=false)
     */
    private $prix;

    /**
     * @var int|null
     * @Assert\NotNull
     * @Assert\NotBlank
     * @ORM\Column(name="id_user", type="integer", nullable=true)
     */
    private $idUser;

    public function getIdH(): ?int
    {
        return $this->idH;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    public function getVille(): ?string
    {
        return $this->ville;
    }

    public function setVille(string $ville): self
    {
        $this->ville = $ville;

        return $this;
    }

    public function getCategorie(): ?string
    {
        return $this->categorie;
    }

    public function setCategorie(string $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }

    public function getCapacite(): ?int
    {
        return $this->capacite;
    }

    public function setCapacite(int $capacite): self
    {
        $this->capacite = $capacite;

        return $this;
    }

    public function getDisponibilite(): ?bool
    {
        return $this->disponibilite;
    }

    public function setDisponibilite(bool $disponibilite): self
    {
        $this->disponibilite = $disponibilite;

        return $this;
    }

    public function getPrix(): ?int
    {
        return $this->prix;
    }

    public function setPrix(int $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getIdUser(): ?int
    {
        return $this->idUser;
    }

    public function setIdUser(?int $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }
    public function jsonSerialize(): array
    {
        return array(
            'id' => $this->idH,
            'name' => $this->name

        );
    }

}
