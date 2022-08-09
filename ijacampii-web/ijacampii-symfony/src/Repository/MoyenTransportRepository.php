<?php

namespace App\Repository;

use App\Entity\MoyenTransport;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<MoyenTransport>
 *
 * @method MoyenTransport|null find($id, $lockMode = null, $lockVersion = null)
 * @method MoyenTransport|null findOneBy(array $criteria, array $orderBy = null)
 * @method MoyenTransport[]    findAll()
 * @method MoyenTransport[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class MoyenTransportRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, MoyenTransport::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(MoyenTransport $entity, bool $flush = true): void
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
    public function remove(MoyenTransport $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }
/*
    public function findByType($txt)
    {
        $entityManager=$this->getEntityManager();
        $query=$entityManager
            ->createQuery("SELECT moy from APP\Entity\MoyenTransport moy where moy.type like :txt")
            ->setParameter('txt','%'.$txt.'%');
        return $query->getResult();
    }
*/
    public function findByType($type){
        return $this->createQueryBuilder('transport')
        ->where('MoyenTransport.type LIKE :type')
        ->setParameter('type', '%'.$type.'%')
        ->getQuery()
        ->getResult();
        }
        

    public function Tri()
    {
        return $this->createQueryBuilder('h')
            ->orderBy('h.type', 'ASC')
            ->getQuery()
            ->getResult()
        ;
    }

    // /**
    //  * @return MoyenTransport[] Returns an array of MoyenTransport objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('m')
            ->andWhere('m.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('m.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?MoyenTransport
    {
        return $this->createQueryBuilder('m')
            ->andWhere('m.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
