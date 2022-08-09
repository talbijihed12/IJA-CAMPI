<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220513002326 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE avis (id_a INT AUTO_INCREMENT NOT NULL, idu INT DEFAULT NULL, commentaire VARCHAR(255) NOT NULL, INDEX idu (idu), PRIMARY KEY(id_a)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE commande (id INT AUTO_INCREMENT NOT NULL, id_utilisateur INT DEFAULT NULL, montant DOUBLE PRECISION NOT NULL, date DATE NOT NULL, adresse VARCHAR(255) NOT NULL, reference VARCHAR(255) NOT NULL, INDEX id_utilisateur (id_utilisateur), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE equipement (id INT AUTO_INCREMENT NOT NULL, id_evenement INT DEFAULT NULL, id_utilisateur INT DEFAULT NULL, nom VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, photo VARCHAR(255) NOT NULL, marque VARCHAR(255) NOT NULL, categorie VARCHAR(255) NOT NULL, prix DOUBLE PRECISION NOT NULL, INDEX id_utilisateur (id_utilisateur), INDEX id_evenement (id_evenement), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE evenement (id_hebergement INT DEFAULT NULL, id_user INT DEFAULT NULL, id_equipement INT DEFAULT NULL, id_transport INT DEFAULT NULL, id_avis INT DEFAULT NULL, id_Event INT AUTO_INCREMENT NOT NULL, nom_Event VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, date_debut VARCHAR(255) NOT NULL, date_fin VARCHAR(255) NOT NULL, nbr_reservation INT NOT NULL, prix INT NOT NULL, activite VARCHAR(255) NOT NULL, INDEX id_transport (id_transport), INDEX id_avis (id_avis), INDEX id_user (id_user), INDEX id_equipement (id_equipement), INDEX id_hebergement (id_hebergement), PRIMARY KEY(id_Event)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE hebergement (id_h INT AUTO_INCREMENT NOT NULL, name VARCHAR(100) NOT NULL, ville VARCHAR(20) NOT NULL, categorie VARCHAR(20) NOT NULL, capacite INT NOT NULL, disponibilite TINYINT(1) NOT NULL, prix INT NOT NULL, id_user INT DEFAULT NULL, INDEX id_user (id_user), PRIMARY KEY(id_h)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE ligne_commande (id INT AUTO_INCREMENT NOT NULL, id_equipement INT DEFAULT NULL, id_commande INT DEFAULT NULL, quantite INT NOT NULL, INDEX id_commande (id_commande), INDEX id_equipement (id_equipement), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE moyen_transport (id_transport INT AUTO_INCREMENT NOT NULL, id_user INT DEFAULT NULL, type VARCHAR(100) NOT NULL, matricule VARCHAR(100) NOT NULL, marque VARCHAR(100) NOT NULL, nbr_place INT NOT NULL, frais DOUBLE PRECISION NOT NULL, INDEX id_user (id_user), INDEX id_user_2 (id_user), PRIMARY KEY(id_transport)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE participement_event (id_participement INT AUTO_INCREMENT NOT NULL, id_user INT DEFAULT NULL, nbr_participement INT NOT NULL, id_Event INT DEFAULT NULL, INDEX id_Event (id_Event), INDEX id_user (id_user), PRIMARY KEY(id_participement)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reclamation (id_r INT AUTO_INCREMENT NOT NULL, idu INT DEFAULT NULL, description VARCHAR(255) NOT NULL, photo VARCHAR(255) NOT NULL, date DATE NOT NULL, etat VARCHAR(255) NOT NULL, INDEX idu (idu), PRIMARY KEY(id_r)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reservation_moyen_transport (id_reservation INT AUTO_INCREMENT NOT NULL, id_transport INT DEFAULT NULL, id_user INT DEFAULT NULL, date_debut DATE NOT NULL, date_fin DATE NOT NULL, place VARCHAR(100) NOT NULL, INDEX id_user (id_user), INDEX id_transport (id_transport), PRIMARY KEY(id_reservation)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reservationevenement (id_re INT AUTO_INCREMENT NOT NULL, id_evenement INT DEFAULT NULL, id_h INT DEFAULT NULL, dateDebut DATE NOT NULL, dateFin DATE NOT NULL, INDEX id_h (id_h), INDEX id_evenement (id_evenement), PRIMARY KEY(id_re)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reservationsimple (id_rs INT AUTO_INCREMENT NOT NULL, id_h INT DEFAULT NULL, dateDebut DATE NOT NULL, dateFin DATE NOT NULL, NameHebergement VARCHAR(50) DEFAULT \'NULL\', User_id INT DEFAULT NULL, INDEX id_user (User_id), INDEX id_h (id_h), PRIMARY KEY(id_rs)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reset_password_request (id INT AUTO_INCREMENT NOT NULL, user INT DEFAULT NULL, selector VARCHAR(20) NOT NULL, hashed_token VARCHAR(100) NOT NULL, requested_at DATETIME NOT NULL COMMENT \'(DC2Type:datetime_immutable)\', expires_at DATETIME NOT NULL COMMENT \'(DC2Type:datetime_immutable)\', INDEX IDX_7CE748A8D93D649 (user), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE utilisateurs (id_user INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, prenom VARCHAR(255) NOT NULL, date_naissance VARCHAR(255) NOT NULL, adresse VARCHAR(255) NOT NULL, num_tel INT NOT NULL, login VARCHAR(255) NOT NULL, mdp VARCHAR(255) NOT NULL, role JSON NOT NULL, is_active TINYINT(1) NOT NULL, PRIMARY KEY(id_user)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE vote (idv INT AUTO_INCREMENT NOT NULL, idu INT DEFAULT NULL, type_v INT NOT NULL, idavis INT NOT NULL, INDEX idu (idu), PRIMARY KEY(idv)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE avis ADD CONSTRAINT FK_8F91ABF099B902AD FOREIGN KEY (idu) REFERENCES utilisateurs (id_user)');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67D50EAE44 FOREIGN KEY (id_utilisateur) REFERENCES utilisateurs (id_user)');
        $this->addSql('ALTER TABLE equipement ADD CONSTRAINT FK_B8B4C6F38B13D439 FOREIGN KEY (id_evenement) REFERENCES evenement (id_Event)');
        $this->addSql('ALTER TABLE equipement ADD CONSTRAINT FK_B8B4C6F350EAE44 FOREIGN KEY (id_utilisateur) REFERENCES utilisateurs (id_user)');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT FK_B26681E5040106B FOREIGN KEY (id_hebergement) REFERENCES hebergement (id_h)');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT FK_B26681E6B3CA4B FOREIGN KEY (id_user) REFERENCES utilisateurs (id_user)');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT FK_B26681E1D3E4624 FOREIGN KEY (id_equipement) REFERENCES equipement (id)');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT FK_B26681EE69E9D09 FOREIGN KEY (id_transport) REFERENCES moyen_transport (id_transport)');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT FK_B26681E4B1B7F2 FOREIGN KEY (id_avis) REFERENCES avis (id_a)');
        $this->addSql('ALTER TABLE ligne_commande ADD CONSTRAINT FK_3170B74B1D3E4624 FOREIGN KEY (id_equipement) REFERENCES equipement (id)');
        $this->addSql('ALTER TABLE ligne_commande ADD CONSTRAINT FK_3170B74B3E314AE8 FOREIGN KEY (id_commande) REFERENCES commande (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE moyen_transport ADD CONSTRAINT FK_F42537D86B3CA4B FOREIGN KEY (id_user) REFERENCES utilisateurs (id_user)');
        $this->addSql('ALTER TABLE participement_event ADD CONSTRAINT FK_B6206E866B3CA4B FOREIGN KEY (id_user) REFERENCES utilisateurs (id_user)');
        $this->addSql('ALTER TABLE participement_event ADD CONSTRAINT FK_B6206E8614EA6493 FOREIGN KEY (id_Event) REFERENCES evenement (id_Event) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT FK_CE60640499B902AD FOREIGN KEY (idu) REFERENCES utilisateurs (id_user)');
        $this->addSql('ALTER TABLE reservation_moyen_transport ADD CONSTRAINT FK_AB358B91E69E9D09 FOREIGN KEY (id_transport) REFERENCES moyen_transport (id_transport)');
        $this->addSql('ALTER TABLE reservation_moyen_transport ADD CONSTRAINT FK_AB358B916B3CA4B FOREIGN KEY (id_user) REFERENCES utilisateurs (id_user)');
        $this->addSql('ALTER TABLE reservationevenement ADD CONSTRAINT FK_B853CF6A8B13D439 FOREIGN KEY (id_evenement) REFERENCES evenement (id_Event)');
        $this->addSql('ALTER TABLE reservationevenement ADD CONSTRAINT FK_B853CF6A56FEAC98 FOREIGN KEY (id_h) REFERENCES hebergement (id_h)');
        $this->addSql('ALTER TABLE reservationsimple ADD CONSTRAINT FK_83C013956FEAC98 FOREIGN KEY (id_h) REFERENCES hebergement (id_h)');
        $this->addSql('ALTER TABLE reset_password_request ADD CONSTRAINT FK_7CE748A8D93D649 FOREIGN KEY (user) REFERENCES utilisateurs (id_user)');
        $this->addSql('ALTER TABLE vote ADD CONSTRAINT FK_5A10856499B902AD FOREIGN KEY (idu) REFERENCES utilisateurs (id_user)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY FK_B26681E4B1B7F2');
        $this->addSql('ALTER TABLE ligne_commande DROP FOREIGN KEY FK_3170B74B3E314AE8');
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY FK_B26681E1D3E4624');
        $this->addSql('ALTER TABLE ligne_commande DROP FOREIGN KEY FK_3170B74B1D3E4624');
        $this->addSql('ALTER TABLE equipement DROP FOREIGN KEY FK_B8B4C6F38B13D439');
        $this->addSql('ALTER TABLE participement_event DROP FOREIGN KEY FK_B6206E8614EA6493');
        $this->addSql('ALTER TABLE reservationevenement DROP FOREIGN KEY FK_B853CF6A8B13D439');
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY FK_B26681E5040106B');
        $this->addSql('ALTER TABLE reservationevenement DROP FOREIGN KEY FK_B853CF6A56FEAC98');
        $this->addSql('ALTER TABLE reservationsimple DROP FOREIGN KEY FK_83C013956FEAC98');
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY FK_B26681EE69E9D09');
        $this->addSql('ALTER TABLE reservation_moyen_transport DROP FOREIGN KEY FK_AB358B91E69E9D09');
        $this->addSql('ALTER TABLE avis DROP FOREIGN KEY FK_8F91ABF099B902AD');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67D50EAE44');
        $this->addSql('ALTER TABLE equipement DROP FOREIGN KEY FK_B8B4C6F350EAE44');
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY FK_B26681E6B3CA4B');
        $this->addSql('ALTER TABLE moyen_transport DROP FOREIGN KEY FK_F42537D86B3CA4B');
        $this->addSql('ALTER TABLE participement_event DROP FOREIGN KEY FK_B6206E866B3CA4B');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY FK_CE60640499B902AD');
        $this->addSql('ALTER TABLE reservation_moyen_transport DROP FOREIGN KEY FK_AB358B916B3CA4B');
        $this->addSql('ALTER TABLE reset_password_request DROP FOREIGN KEY FK_7CE748A8D93D649');
        $this->addSql('ALTER TABLE vote DROP FOREIGN KEY FK_5A10856499B902AD');
        $this->addSql('DROP TABLE avis');
        $this->addSql('DROP TABLE commande');
        $this->addSql('DROP TABLE equipement');
        $this->addSql('DROP TABLE evenement');
        $this->addSql('DROP TABLE hebergement');
        $this->addSql('DROP TABLE ligne_commande');
        $this->addSql('DROP TABLE moyen_transport');
        $this->addSql('DROP TABLE participement_event');
        $this->addSql('DROP TABLE reclamation');
        $this->addSql('DROP TABLE reservation_moyen_transport');
        $this->addSql('DROP TABLE reservationevenement');
        $this->addSql('DROP TABLE reservationsimple');
        $this->addSql('DROP TABLE reset_password_request');
        $this->addSql('DROP TABLE utilisateurs');
        $this->addSql('DROP TABLE vote');
    }
}
