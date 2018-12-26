
package application;

public class Resultados {
	private float sumFx;
	private float[] angulos;
	private float sumFy;
	private float[] Newtons;
	private float VResultante, AnguloResult;

	public void setSumFx(float[] fuerzas, float[] angulos, int n) {
		this.Newtons = new float[n];
		this.angulos = new float[n];
		float sumFx = (float) (fuerzas[0] * (Math.cos(Math.toRadians(angulos[0]))));
		for (int i = 1; i < n; i++) {
			sumFx += (float) (fuerzas[i] * (Math.cos(Math.toRadians(angulos[i]))));
		}
		this.sumFx = sumFx;
	}

	public void setSumFxV2(float[] fuerzas, float[] angulos,float[]fx, int[] fun1, int n) {
		this.Newtons = new float[n];
		this.angulos = new float[n];
		// Sen =0
		// Cos =1

		float sumFx=0;
		if (fun1[0] == 0) {
			sumFx = (float) (fuerzas[0] * (Math.sin(Math.toRadians(angulos[0]))));

		} else if(fun1[0]==1){
			sumFx = (float) (fuerzas[0] * (Math.cos(Math.toRadians(angulos[0]))));
		}else {
			sumFx=(float)(fuerzas[0]);
		}
		
		if(fx[0]<0) {
			sumFx*=-1;
		}
		for (int i = 1; i < n; i++) {
			float resSen= (float) (fuerzas[i] * (Math.sin(Math.toRadians(angulos[i]))));
			float resCos=(float) (fuerzas[i] * (Math.cos(Math.toRadians(angulos[i]))));
			if (fun1[i] == 0) {
				if(fx[i]<0) {
					sumFx -= resSen;
				}else {
					sumFx += resSen;
				}
			} else if(fun1[i]==1){
				if(fx[i]<0) {
					sumFx -= resCos;

				}else {
					sumFx += resCos;
				}
			}else {
				if(fx[i]<0) {
					sumFx-=(float)(fuerzas[i]);
				}else if(fx[i]>0){
					sumFx+=(float)(fuerzas[i]);
				}
			}
		}
		this.sumFx = sumFx;
	}

	public float getSumFx() {
		return this.sumFx;
	}

	public void setSumFy(float[] fuerzas, float[] angulos, int n) {
		this.Newtons = new float[n];
		this.angulos = new float[n];
		float sumFy = (float) (fuerzas[0] * (Math.sin(Math.toRadians(angulos[0]))));
		for (int i = 1; i < n; i++) {
			sumFy += (float) (fuerzas[i] * (Math.sin(Math.toRadians(angulos[i]))));
		}
		this.sumFy = sumFy;

	}
	
	public void setSumFyV2(float[] fuerzas, float[] angulos,float[] fy,int[] fun2, int n) {
		this.Newtons = new float[n];
		this.angulos = new float[n];
		float sumFy=0;
		if (fun2[0] == 0) {
			sumFy=(float) (fuerzas[0] * (Math.sin(Math.toRadians(angulos[0]))));
		}else if(fun2[0]==1) {
			sumFy=(float) (fuerzas[0] * (Math.cos(Math.toRadians(angulos[0]))));
		}
		
		if(fy[0]<0) {
			sumFy*=-1;
		}
		
		
		for (int i = 1; i < n; i++) {
			float resSen= (float) (fuerzas[i] * (Math.sin(Math.toRadians(angulos[i]))));
			float resCos=(float) (fuerzas[i] * (Math.cos(Math.toRadians(angulos[i]))));
			
			if (fun2[i] == 0) {
				if(fy[i]<0) {
					sumFy-=resSen;
				}else {
					sumFy+=resSen;
				}
			}else if(fun2[i]==1){
				if(fy[i]<0) {
					sumFy-=resCos;
				}else {
					sumFy+=resCos;
				}
			}else {
				if(fy[i]<0) {
					sumFy-=(float)(fuerzas[i]);
				}else if(fy[i]>0) {
					sumFy+=(float)(fuerzas[i]);
				}

			}
		}
		this.sumFy = sumFy;

	}

	public float getSumFy() {
		return this.sumFy;
	}

	public void setVR() {
		this.VResultante = (float) (Math.sqrt((Math.pow(this.sumFx, 2) + Math.pow(this.sumFy, 2))));
	}

	public float getVR() {
		return this.VResultante;
	}

	void setAnguloResult() {
		this.AnguloResult = (float) (Math.toDegrees(Math.atan(this.sumFy / this.sumFx)));
	}

	public float getAnguloResult() {
		return this.AnguloResult;
	}

	public void setAngulos(int n) {
		angulos = new float[n];
		this.angulos = angulos;
	}

	public float[] getAngulos() {
		return this.angulos;
	}
}
