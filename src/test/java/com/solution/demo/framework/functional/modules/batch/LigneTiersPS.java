package com.solution.demo.framework.functional.modules.batch;

import java.util.Objects;

/**
 * Une ligne Tiers PS
 */
public class LigneTiersPS {
    private String codeProprio;
    private String numPS;
    private String raisonSocial;

    public LigneTiersPS(String codeProprio, String numPS, String raisonSocial) {
        this.codeProprio = codeProprio;
        this.numPS = numPS;
        this.raisonSocial = raisonSocial;
    }

    public String getCodeProprio() {
        return codeProprio;
    }

    public void setCodeProprio(String codeProprio) {
        this.codeProprio = codeProprio;
    }

    public String getNumPS() {
        return numPS;
    }

    public void setNumPS(String numPS) {
        this.numPS = numPS;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LigneTiersPS that = (LigneTiersPS) o;
        return Objects.equals(codeProprio, that.codeProprio) && Objects.equals(numPS, that.numPS) && Objects.equals(raisonSocial, that.raisonSocial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeProprio, numPS, raisonSocial);
    }

    @Override
    public String toString() {
        return "LigneTiersPS{" +
                "codeProprio='" + codeProprio + '\'' +
                ", numPS='" + numPS + '\'' +
                ", raisonSocial='" + raisonSocial + '\'' +
                '}';
    }
}
