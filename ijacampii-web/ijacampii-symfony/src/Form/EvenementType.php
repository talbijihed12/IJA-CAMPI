<?php

namespace App\Form;

use App\Entity\Equipement;
use App\Entity\Evenement;
use App\Entity\MoyenTransport;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Date;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use App\Entity\Hebergement;

class EvenementType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nomEvent',TextType::class, array('label' => 'Event name'),[

                ]



            )
            ->add('description',TextareaType::class,['attr' =>
                [
                    'rows' =>10,'cols ' =>50

                ]
            ])
            ->add('dateDebut',TextType::class,[
        //'widget' => 'single_text'
    ])
            ->add('dateFin',TextType::class,[
                //'widget' => 'single_text'
            ])
            ->add('nbrReservation',TextType::class,[
                'attr'=>[
                    'placeholder'=>"nombre de réservation"
                    ]
                ])
            ->add('prix',TextType::class,[
                'attr'=>[
                    'placeholder'=>"prix d'évènement"
                ]
            ])
            ->add('activite',TextType::class,[
                'attr'=>[
                    'placeholder'=>"les activités"
                ]
            ])
            ->add('idHebergement', EntityType::class,[
                'class' => Hebergement::class,
                'choice_label' => 'ville',
            ])

            ->add('idEquipement', EntityType::class,[
                'class' => Equipement::class,
                'choice_label' => 'nom',
            ])
            ->add('idTransport', EntityType::class,[
                'class' => MoyenTransport::class,
                'choice_label' => 'type',
            ])

        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Evenement::class,
        ]);
    }
}
