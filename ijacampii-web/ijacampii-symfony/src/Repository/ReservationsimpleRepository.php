<?php

namespace App\Repository;

use App\Entity\Reservationsimple;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;
use DateTime;

/**
 * @method Reservationsimple|null find($id, $lockMode = null, $lockVersion = null)
 * @method Reservationsimple|null findOneBy(array $criteria, array $orderBy = null)
 * @method Reservationsimple[]    findAll()
 * @method Reservationsimple[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ReservationsimpleRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Reservationsimple::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Reservationsimple $entity, bool $flush = true): void
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
    public function remove(Reservationsimple $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    // /**
    //  * @return Reservationsimple[] Returns an array of Reservationsimple objects
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
    public function findOneBySomeField($value): ?Reservationsimple
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    public function AfficherReservationParDateDebut(DateTime $date1, DateTime $date2): array
    {
        $entityManager = $this->getEntityManager();

        $query = $entityManager->createQuery(
            'SELECT rs
             FROM App\Entity\Reservationsimple rs
             WHERE rs.datedebut between :date1 and :date2 '
        )
        ->setParameter('date1', $date1)
        ->setParameter('date2', $date2);

        // returns an array of Product objects
        return $query->getResult();
    }
    public function AfficherReservationParDateFin(DateTime $date1, DateTime $date2): array
    {
        $entityManager = $this->getEntityManager();

        $query = $entityManager->createQuery(
            'SELECT rs
             FROM App\Entity\Reservationsimple rs
             WHERE rs.datefin between :date1 and :date2 '
        )
        ->setParameter('date1', $date1)
        ->setParameter('date2', $date2);

        // returns an array of Product objects
        return $query->getResult();
    }
    // public function AfficherReservationParDateDebut(DateTime $date1, DateTime $date2): array
    // {
    //     $conn = $this->getEntityManager()->getConnection();

    //     $sql = '
    //     SELECT * FROM reservationsimple WHERE dateDebut between :date1 and :date2
    //         ';
    //     $stmt = $conn->prepare($sql);
    //     $resultSet = $stmt->executeQuery(['date1' => $date1],['date2' => $date2]);

    //     // returns an array of arrays (i.e. a raw data set)
    //     return $resultSet->fetchAllAssociative();
    // }
    
}
