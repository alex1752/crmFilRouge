CREATE TABLE Clients
    (id SERIAL,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    entreprise VARCHAR(200),
    email VARCHAR(200) NOT NULL UNIQUE,
    telephone VARCHAR(200),
    mobile VARCHAR(200),
    actif BOOLEAN NOT NULL DEFAULT True,
    notes TEXT,
    CONSTRAINT pk_clients PRIMARY KEY (id),
    CONSTRAINT clientEmailValide CHECK (email ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'));

CREATE TABLE Orders
    (id SERIAL,
    label VARCHAR(200),
    tjmHT DECIMAL(10,2) NOT NULL,
    dureeJours DECIMAL NOT NULL,
    TVA DECIMAL(10,2) NOT NULL,
    statut VARCHAR(200) NOT NULL,
    typeCommande VARCHAR(200) NOT NULL,
    notes TEXT,
    idClient INTEGER NOT NULL,
    CONSTRAINT pk_commandes PRIMARY KEY (id),
    CONSTRAINT coutNonNegatif CHECK (tjmHT>0),
    CONSTRAINT dureeNonNulle CHECK (dureeJours>0),
    CONSTRAINT TVAValide CHECK (TVA>0 AND TVA<100),
    CONSTRAINT fk_commandes_clients FOREIGN KEY(idClient) REFERENCES clients(id) ON DELETE CASCADE);

CREATE TABLE Users
    (id SERIAL,
    login VARCHAR(200) NOT NULL,
    motDePasse VARCHAR(200) NOT NULL,
    email VARCHAR(200) UNIQUE,
    CONSTRAINT pk_utilisateurs PRIMARY KEY (id),
    CONSTRAINT utilisateurEmailValide CHECK (email ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'));
	
CREATE TABLE Background
    (id SERIAL,
    login VARCHAR (200) NOT NULL,
    actions VARCHAR (200) NOT NULL,
	elementModifier VARCHAR (200) NOT NULL,
    idModif INTEGER NOT NULL,
    CONSTRAINT pk_background PRIMARY KEY (id));
