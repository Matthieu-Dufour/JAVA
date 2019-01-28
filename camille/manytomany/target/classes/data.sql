INSERT INTO intervenant (intervenant_id, nom) VALUES (1, 'Anne');
INSERT INTO intervenant (intervenant_id, nom) VALUES (2, 'Paul');
INSERT INTO intervenant (intervenant_id, nom) VALUES (3, 'Richard');
	
INSERT INTO projet (projet_id, lieu, projet_date) VALUES (1, 'Nancy', '2018-09-20');
INSERT INTO projet (projet_id, lieu, projet_date) VALUES (2, 'Metz', '2018-12-05');
	
INSERT INTO intervenants_projets (intervenant_id, projet_id) VALUES (1, 1);
INSERT INTO intervenants_projets (intervenant_id, projet_id) VALUES (1, 2);
INSERT INTO intervenants_projets (intervenant_id, projet_id) VALUES (2, 1);
INSERT INTO intervenants_projets (intervenant_id, projet_id) VALUES (3, 2);
	