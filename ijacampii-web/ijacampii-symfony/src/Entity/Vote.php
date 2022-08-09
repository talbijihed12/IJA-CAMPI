<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Vote
 *
 * @ORM\Table(name="vote", indexes={@ORM\Index(name="idu", columns={"idu"})})
 * @ORM\Entity
 */
class Vote
{
    /**
     * @var int
     *
     * @ORM\Column(name="idv", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idv;

    /**
     * @var int
     *
     * @ORM\Column(name="type_v", type="integer", nullable=false)
     */
    private $typeV;

    /**
     * @var int
     *
     * @ORM\Column(name="idavis", type="integer", nullable=false)
     */
    private $idavis;

    /**
     * @var \Utilisateurs
     *
     * @ORM\ManyToOne(targetEntity="Utilisateurs")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idu", referencedColumnName="id_user")
     * })
     */
    private $idu;

    public function getIdv(): ?int
    {
        return $this->idv;
    }

    public function getTypeV(): ?int
    {
        return $this->typeV;
    }

    public function setTypeV(int $typeV): self
    {
        $this->typeV = $typeV;

        return $this;
    }

    public function getIdavis(): ?int
    {
        return $this->idavis;
    }

    public function setIdavis(int $idavis): self
    {
        $this->idavis = $idavis;

        return $this;
    }

    public function getIdu(): ?Utilisateurs
    {
        return $this->idu;
    }

    public function setIdu(?Utilisateurs $idu): self
    {
        $this->idu = $idu;

        return $this;
    }


}
