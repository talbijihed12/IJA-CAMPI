<?php

namespace App\Repository;

use App\Entity\ReservationMoyenTransport;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;
use DateTime;

/**
 * @extends ServiceEntityRepository<ReservationMoyenTransport>
 *
 * @method ReservationMoyenTransport|null find($id, $lockMode = null, $lockVersion = null)
 * @method ReservationMoyenTransport|null findOneBy(array $criteria, array $orderBy = null)
 * @method ReservationMoyenTransport[]    findAll()
 * @method ReservationMoyenTransport[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ReservationMoyenTransportRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, ReservationMoyenTransport::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(ReservationMoyenTransport $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(ReservationMoyenTransport $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    public function checkDateDebut(DateTime $dateDebut1, DateTime $dateDebut2): array
    {
        $entityManager = $this->getEntityManager();

        $query = $entityManager->createQuery(
            'SELECT reservation
             FROM App\Entity\ReservationMoyenTransport reservation
             WHERE reservation.dateDebut between :dateDebut1 and :dateDebut2'
        )
        ->setParameter('dateDebut1', $dateDebut1)
        ->setParameter('dateDebut2', $dateDebut2);

        // returns an array of Product objects
        return $query->getResult();
    }


    public function checkDateFin(DateTime $dateFin1, DateTime $dateFin2): array
    {
        $entityManager = $this->getEntityManager();

        $query = $entityManager->createQuery(
            'SELECT reservation
             FROM App\Entity\ReservationMoyenTransport reservation
             WHERE reservation.dateFin between :dateFin1 and :dateFin2 '
        )
        ->setParameter('dateFin1', $dateFin1)
        ->setParameter('dateFin2', $dateFin2);

        // returns an array of Product objects
        return $query->getResult();
    }


    // /**
    //  * @return ReservationMoyenTransport[] Returns an array of ReservationMoyenTransport objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('r.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?ReservationMoyenTransport
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
