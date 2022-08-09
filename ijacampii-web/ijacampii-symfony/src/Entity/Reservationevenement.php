<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reservationevenement
 *
 * @ORM\Table(name="reservationevenement", indexes={@ORM\Index(name="id_h", columns={"id_h"}), @ORM\Index(name="id_evenement", columns={"id_evenement"})})
 * @ORM\Entity
 */
class Reservationevenement
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_re", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idRe;

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
     * @var \Evenement
     *
     * @ORM\ManyToOne(targetEntity="Evenement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_evenement", referencedColumnName="id_Event")
     * })
     */
    private $idEvenement;

    /**
     * @var \Hebergement
     *
     * @ORM\ManyToOne(targetEntity="Hebergement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_h", referencedColumnName="id_h")
     * })
     */
    private $idH;

    public function getIdRe(): ?int
    {
        return $this->idRe;
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

    public function getIdEvenement(): ?Evenement
    {
        return $this->idEvenement;
    }

    public function setIdEvenement(?Evenement $idEvenement): self
    {
        $this->idEvenement = $idEvenement;

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
