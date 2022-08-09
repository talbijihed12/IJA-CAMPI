<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * ReservationMoyenTransport
 *
 * @ORM\Table(name="reservation_moyen_transport", indexes={@ORM\Index(name="id_user", columns={"id_user"}), @ORM\Index(name="id_transport", columns={"id_transport"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\ReservationMoyenTransportRepository")
 */
class ReservationMoyenTransport
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_reservation", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("post:read") 
     */
    private $idReservation;

    /**
     * @var \DateTime
     * @Assert\Date
     * @Assert\GreaterThanOrEqual("today")
     * @ORM\Column(name="date_debut", type="date", nullable=false)
     * @Groups("post:read") 
     */
    private $dateDebut;

    /**
     * @var \DateTime
     * @Assert\Date
     * @Assert\GreaterThanOrEqual(propertyPath="dateDebut",message="La date fin doit être supérieure à la date début")
     * @ORM\Column(name="date_fin", type="date", nullable=false)
     * @Groups("post:read") 
     */
    private $dateFin;

    /**
     * @var string
     * @Assert\NotBlank(message="Place doit être non vide")
     * @ORM\Column(name="place", type="string", length=100, nullable=false)
     * @Groups("post:read") 
     */
    private $place;

    /**
     * @var \MoyenTransport
     *
     * @ORM\ManyToOne(targetEntity="MoyenTransport")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_transport", referencedColumnName="id_transport")
     * })
     * @Groups("post:read") 
     */
    private $idTransport;

    /**
     * @var \Utilisateurs
     *
     * @ORM\ManyToOne(targetEntity="Utilisateurs")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_user", referencedColumnName="id_user")
     * })
     * @Groups("post:read") 
     */
    private $idUser;

    protected $captchaCode;

    public function getCaptchaCode()
    {
        return $this->captchaCode;
    }

    public function setCaptchaCode($captchaCode)
    {
        $this->captchaCode = $captchaCode;
    }


    public function getIdReservation(): ?int
    {
        return $this->idReservation;
    }

    public function getDateDebut(): ?\DateTimeInterface
    {
        return $this->dateDebut;
    }

    public function setDateDebut(\DateTimeInterface $dateDebut): self
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    public function getDateFin(): ?\DateTimeInterface
    {
        return $this->dateFin;
    }

    public function setDateFin(\DateTimeInterface $dateFin): self
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    public function getPlace(): ?string
    {
        return $this->place;
    }

    public function setPlace(string $place): self
    {
        $this->place = $place;

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

    public function getIdUser(): ?Utilisateurs
    {
        return $this->idUser;
    }

    public function setIdUser(?Utilisateurs $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }


}
