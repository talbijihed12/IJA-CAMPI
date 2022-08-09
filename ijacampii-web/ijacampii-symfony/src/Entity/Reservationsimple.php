<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reservationsimple
 *
 * @ORM\Table(name="reservationsimple", indexes={@ORM\Index(name="id_user", columns={"User_id"}), @ORM\Index(name="id_h", columns={"id_h"})})
 * @ORM\Entity(repositoryClass="App\Repository\ReservationsimpleRepository")
 */
class Reservationsimple
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_rs", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idRs;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateDebut", type="date", nullable=false)
     */
    private $datedebut;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateFin", type="date", nullable=false)
     */
    private $datefin;

    /**
     * @var string|null
     *
     * @ORM\Column(name="NameHebergement", type="string", length=50, nullable=true, options={"default"="NULL"})
     */
    private $namehebergement = 'NULL';

    /**
     * @var int|null
     *
     * @ORM\Column(name="User_id", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $userId = NULL;

    /**
     * @var \Hebergement
     *
     * @ORM\ManyToOne(targetEntity="Hebergement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_h", referencedColumnName="id_h")
     * })
     */
    private $idH;

    public function getIdRs(): ?int
    {
        return $this->idRs;
    }

    public function getDatedebut(): ?\DateTimeInterface
    {
        return $this->datedebut;
    }

    public function setDatedebut(\DateTimeInterface $datedebut): self
    {
        $this->datedebut = $datedebut;

        return $this;
    }

    public function getDatefin(): ?\DateTimeInterface
    {
        return $this->datefin;
    }

    public function setDatefin(\DateTimeInterface $datefin): self
    {
        $this->datefin = $datefin;

        return $this;
    }

    public function getNamehebergement(): ?string
    {
        return $this->namehebergement;
    }

    public function setNamehebergement(?string $namehebergement): self
    {
        $this->namehebergement = $namehebergement;

        return $this;
    }

    public function getUserId(): ?int
    {
        return $this->userId;
    }

    public function setUserId(?int $userId): self
    {
        $this->userId = $userId;

        return $this;
    }

    public function getIdH(): ?Hebergement
    {
        return $this->idH;
    }

    public function setIdH(?Hebergement $idH): self
    {
        $this->idH = $idH;

        return $this;
    }


}
