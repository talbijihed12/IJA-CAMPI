<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use PHPUnit\Util\Json;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Equipement
 *
 * @ORM\Table(name="equipement", indexes={@ORM\Index(name="id_utilisateur", columns={"id_utilisateur"}), @ORM\Index(name="id_evenement", columns={"id_evenement"})})
 * @ORM\Entity(repositoryClass="App\Repository\EquipementRepository")
 */
class Equipement implements \JsonSerializable
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;
    public function __toString() {
        return strval($this->id);
    }
    /**
     * @var string
     * @Assert\NotBlank(message="ce champs est obligatoire")
     * @Assert\Length(
     *      min = 2,
     *      max = 255,
     *      minMessage = "le nom doit avoir {{ limit }} au caractère minimum",
     *      maxMessage = "le nom doit avoir {{ limit }} au caractère maximum"
     * )
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     */
    private $nom;

    /**
     * @var string
     * @Assert\NotBlank(message="ce champs est obligatoire")
     * @Assert\Length(
     *      min = 10,
     *      max = 255,
     *      minMessage = "la description doit avoir {{ limit }}  caractère au minimum",
     *      maxMessage = "la description doit avoir {{ limit }}  caractère au maximum"
     * )
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var string
     * @Assert\NotBlank(message="ce champs est obligatoire")
     * @ORM\Column(name="photo", type="string", length=255, nullable=false)
     */
    private $photo;

    /**
     * @var string
     *@Assert\NotBlank(message="ce champs est obligatoire")
     * @Assert\Length(
     *      min = 10,
     *      max = 255,
     *      minMessage = "la marque doit avoir {{ limit }}  caractère au minimum",
     *      maxMessage = "la marque doit avoir {{ limit }}  caractère au maximum"
     * )
     * @ORM\Column(name="marque", type="string", length=255, nullable=false)
     */
    private $marque;

    /**
     * @var string
     * @Assert\NotBlank(message="ce champs est obligatoire")
     * @Assert\Length(
     *      min = 10,
     *      max = 255,
     *      minMessage = "la categorie doit avoir {{ limit }}  caractère au minimum",
     *      maxMessage = "la categorie doit avoir {{ limit }}  caractère au maximum"
     * )
     * @ORM\Column(name="categorie", type="string", length=255, nullable=false)
     */
    private $categorie;

    /**
     * @var float
     * @Assert\Type(message="Entrez un prix valide", type="float")
     * @Assert\NotBlank(message="ce champs est obligatoire")
     * @Assert\GreaterThanOrEqual(message="le prix doit être supérieure a zéro!", value = 0)
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=false)
     */
    private $prix;

    /**
     * @var \Evenement
     *
     * @ORM\ManyToOne(targetEntity="Evenement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_evenement", referencedColumnName="id_Event")
     * })
     */
    private $idEvenement;

    /**
     * @var \Utilisateurs
     *
     * @ORM\ManyToOne(targetEntity="Utilisateurs")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_utilisateur", referencedColumnName="id_user")
     * })
     */
    private $idUtilisateur;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getPhoto(): ?string
    {
        return $this->photo;
    }

    public function setPhoto(string $photo): self
    {
        $this->photo = $photo;

        return $this;
    }

    public function getMarque(): ?string
    {
        return $this->marque;
    }

    public function setMarque(string $marque): self
    {
        $this->marque = $marque;

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

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getIdEvenement(): ?Evenement
    {
        return $this->idEvenement;
    }

    public function setIdEvenement(?Evenement $idEvenement): self
    {
        $this->idEvenement = $idEvenement;

        return $this;
    }

    public function getIdUtilisateur(): ?Utilisateurs
    {
        return $this->idUtilisateur;
    }

    public function setIdUtilisateur(?Utilisateurs $idUtilisateur): self
    {
        $this->idUtilisateur = $idUtilisateur;

        return $this;
    }


    public function jsonSerialize(): array
    {
        return array(
            'id' => $this->id,
            'nom' => $this->nom

        );
    }

}
