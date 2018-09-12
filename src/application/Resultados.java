
package application;

public class Resultados {
    private float sumFx;
    private float[] angulos;
    private float sumFy;
    private float[] Newtons;
    private float VResultante,AnguloResult;

    public void setSumFx(float[] fuerzas,float[] angulos,int n){
        this.Newtons = new float[n];
        this.angulos = new float[n];
                float sumFx = (float)(fuerzas[0]*(Math.cos(Math.toRadians(angulos[0]))));
        for(int i=1;i<n;i++){
	    sumFx+= (float)(fuerzas[i]*(Math.cos(Math.toRadians(angulos[i]))));
        }
        this.sumFx=sumFx;
    }
    
    public float getSumFx(){
        return this.sumFx;
    }
    
    public void setSumFy(float[]fuerzas,float[] angulos,int n){
    this.Newtons = new float[n];
        this.angulos = new float[n];
                float sumFy = (float)(fuerzas[0]*(Math.sin(Math.toRadians(angulos[0]))));
        for(int i=1;i<n;i++){
	    sumFy+= (float)(fuerzas[i]*(Math.sin(Math.toRadians(angulos[i]))));
        }
        this.sumFy=sumFy;
    
    }
    public float getSumFy(){
        return this.sumFy;
    }
    
    public void setVR() {
		this.VResultante = (float)(Math.sqrt(   (Math.pow(this.sumFx,2) + Math.pow(this.sumFy,2))   ));
	}
    public float getVR(){
        return this.VResultante;
    }
    
    
    void setAnguloResult () {
		this.AnguloResult = (float) (Math.toDegrees(Math.atan(this.sumFy/this.sumFx)));
	}
    public float getAnguloResult(){
        return this.AnguloResult;
    }
    
    
    
    
    public void setAngulos(int n){
        angulos = new float[n];
        this.angulos=angulos;
    }
    public float[] getAngulos(){
        return this.angulos;
    }
}
