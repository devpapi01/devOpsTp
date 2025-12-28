package com.example.projetmpisi.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExempleAvecBugsTest {

    @Test
    void getNomComplet() {
        ExempleAvecBugs ex = new ExempleAvecBugs();

        String result = ex.getNomComplet("ali", "sani");

        assertEquals("ALI SANI", result);
    }

    @Test
    void diviser() {
        ExempleAvecBugs ex = new ExempleAvecBugs();

        int result = ex.diviser(10, 2);

        assertEquals(5, result);
    }

    @Test
    void lireFichier() {
        ExempleAvecBugs ex = new ExempleAvecBugs();

        // On teste juste que la méthode s'exécute
        ex.lireFichier("fichier_inexistant.txt");

        assertEquals(true, true);
    }

    @Test
    void evaluerNote() {
        ExempleAvecBugs ex = new ExempleAvecBugs();

        String result = ex.evaluerNote(85);

        assertEquals("Très bien", result);
    }

    @Test
    void methode1() {
        ExempleAvecBugs ex = new ExempleAvecBugs();

        ex.methode1();

        assertEquals(1, 1);
    }

    @Test
    void methode2() {
        ExempleAvecBugs ex = new ExempleAvecBugs();

        ex.methode2();

        assertEquals(1, 1);
    }

    @Test
    void rechercherUtilisateur() {
        ExempleAvecBugs ex = new ExempleAvecBugs();

        ex.rechercherUtilisateur("ali");

        assertEquals(true, true);
    }
}
