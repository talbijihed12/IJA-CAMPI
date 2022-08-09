<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use JsonSerializable;



/**
 * Evenement
 * 
 * @ORM\Table(name="evenement", indexes={@ORM\Index(name="id_transport", columns={"id_transport"}), @ORM\Index(name="id_avis", columns={"id_avis"}), @ORM\Index(name="id_user", columns={"id_user"}), @ORM\Index(name="id_equipement", columns={"id_equipement"}), @ORM\Index(name="id_hebergement", columns={"id_hebergement"})})
 * @ORM\Entity
 */
class Evenement implements JsonSerializable
{
    /**
     * @var int
     * @ParamConverter("id_Event", class="Event", options={"id_Event": "id_Event"})
     * @ORM\Column(name="id_Event", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idEvent;

    /**
     * @var string
     * @Assert\NotBlank
     * @Assert\NotNull
     * @ORM\Column(name="nom_Event", type="string", length=255, nullable=false)
     */
    public $nomEvent;
    public function __toString() :string {
        return $this->nomEvent;


    }


    /**
     * @var string
     * @Assert\NotBlank
     * @Assert\NotNull
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var string
     * @Assert\NotBlank
     * @Assert\NotNull
     * @ORM\Column(name="date_debut", type="string", length=255, nullable=false)
     */
    private $dateDebut;
    public function __toStringd() :string {
        return $this->dateDebut;
    }

    /**
     * @var string
     * @Assert\NotBlank
     * @Assert\NotNull
     * @ORM\Column(name="date_fin", type="string", length=255, nullable=false)
     */
    private $dateFin;
    public function __toStringf() :string {
        return $this->dateFin;
    }

    /**
     * @var int
     * @Assert\NotBlank
     * @Assert\NotNull
     * @Assert\Positive
     * @ORM\Column(name="nbr_reservation", type="integer", nullable=false)
     */
    private $nbrReservation;

    /**
     * @var int
     * @Assert\NotNull
     * @Assert\Positive
     * @Assert\NotBlank
     * @ORM\Column(name="prix", type="integer", nullable=false)
     */
    private $prix;

    /**
     * @var string
     * @Assert\NotBlank
     * @Assert\NotNull
     * @ORM\Column(name="activite", type="string", length=255, nullable=false)
     */
    private $activite;

    /**
     * @var \Hebergement
     * @Assert\NotBlank(message="les activités doit etres remplits")
     * @Assert\NotNull
     * @ORM\ManyToOne(targetEntity="Hebergement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_hebergement", referencedColumnName="id_h")
     * })
     */
    private $idHebergement;

    /**
     * @var \Utilisateurs
     *@Assert\NotBlank
     * @ORM\ManyToOne(targetEntity="Utilisateurs")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_user", referencedColumnName="id_user")
     * })
     */
    private $idUser;

    /**
     * @var \Equipement
     *@Assert\NotBlank
     * @ORM\ManyToOne(targetEntity="Equipement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_equipement", referencedColumnName="id")
     * })
     */
    private $idEquipement;

    /**
     * @var \MoyenTransport
     *@Assert\NotBlank
     * @ORM\ManyToOne(targetEntity="MoyenTransport")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_transport", referencedColumnName="id_transport")
     * })
     */
    private $idTransport;

    /**
     * @var \Avis
     * @
     * @ORM\ManyToOne(targetEntity="Avis")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_avis", referencedColumnName="id_a")
     * })
     */
    private $idAvis;

    /**
     * @param int $idEvent
     */


    public function getIdEvent(): ?int
    {
        return $this->idEvent;
    }

    public function getNomEvent(): ?string
    {
        return $this->nomEvent;
    }

    public function setNomEvent(string $nomEvent): self
    {
        $this->nomEvent = $nomEvent;

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

    public function getDateDebut(): ?string
    {
        return $this->dateDebut;
    }

    public function setDateDebut(string $dateDebut): self
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    public function getDateFin(): ?string
    {
        return $this->dateFin;
    }

    public function setDateFin(string $dateFin): self
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    public function getNbrReservation(): ?int
    {
        return $this->nbrReservation;
    }

    public function setNbrReservation(int $nbrReservation): self
    {
        $this->nbrReservation = $nbrReservation;

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

    public function getActivite(): ?string
    {
        return $this->activite;
    }

    public function setActivite(string $activite): self
    {
        $this->activite = $activite;

        return $this;
    }

    public function getIdHebergement(): ?Hebergement
    {
        return $this->idHebergement;
    }

    public function setIdHebergement(?Hebergement $idHebergement): self
    {
        $this->idHebergement = $idHebergement;

        return $this;
    }

    public function getIdUser(): ?Utilisateurs
    {
        return $this->idUser;
    }

    public function setIdUser(?Utilisateurs $idUser): self
    {
        $this->idUser = $idUser;

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

    public function getIdTransport(): ?MoyenTransport
    {
        return $this->idTransport;
    }

    public function setIdTransport(?MoyenTransport $idTransport): self
    {
        $this->idTransport = $idTransport;

        return $this;
    }

    public function getIdAvis(): ?Avis
    {
        return $this->idAvis;
    }

    public function setIdAvis(?Avis $idAvis): self
    {
        $this->idAvis = $idAvis;

        return $this;
    }

    public function jsonSerialize(): array
    {
        return array(
            'id' => $this->idEvent,
            'hebergement' => $this->idHebergement,
            'utilisateurs' => $this->idUser,
            'equipement' => $this->idEquipement->jsonSerialize(),
            'moyenTransport' => $this->idTransport,
            'nomEvent' => $this->nomEvent,
            'description' => $this->description,
            'dateDebut' => $this->dateDebut,
            'dateFin' => $this->dateFin,
            'nbrReservation' => $this->nbrReservation,
            'prix' => $this->prix,
            'activite' => $this->activite

        );
    }

    public function setUp($hebergement, $utilisateurs, $equipement, $moyenTransport, $nomEvent, $description, $dateDebut, $dateFin, $nbrReservation, $prix, $activite)
    {
        $this->idHebergement = $hebergement;
        $this->idUser = $utilisateurs;
        $this->idEquipement = $equipement;
        $this->idTransport = $moyenTransport;
        $this->nomEvent = $nomEvent;
        $this->description = $description;
        $this->dateDebut = $dateDebut;
        $this->dateFin = $dateFin;
        $this->nbrReservation = $nbrReservation;
        $this->prix = $prix;
        $this->activite = $activite;

    }

}
