/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trasportoprodotti;

import java.util.List;

/**
 *
 * @author TommasoBotta
 */
public class Veicolo {
    private String id;
    private List misure;

    public Veicolo(String id, List misure) {
        this.id = id;
        this.misure = misure;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getMisure() {
        return misure;
    }

    public void setMisure(List misure) {
        this.misure = misure;
    }
    
}
