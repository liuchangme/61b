public class NBody{
  public static double readRadius(String fileName){
    In in = new In(fileName);
    int firstItemInFile = in.readInt();
    double secondItemInFile = in.readDouble();
    return secondItemInFile;
  }
  public static Planet[] readPlanets(String fileName){
    In in = new In(fileName);

    int num = in.readInt();
    double r = in.readDouble();
    Planet[] planets = new Planet[num];
    for(int count=0;count<num;count+=1){
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String imgFileName = in.readString();
      planets[count] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
    }
    return planets;
  }
  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    Planet[] planets = readPlanets(filename);
    double scale = readRadius(filename);


    String bgimageToDraw = "images/starfield.jpg";
    StdDraw.enableDoubleBuffering();
    StdDraw.setScale(-scale, scale);

    /* Clears the drawing window. */
    StdDraw.clear();

    for(double time = 0; time<T; time +=dt){
      double[] xForces = new double[planets.length];
      double[] yForces = new double[planets.length];
      for(int i=0;i<planets.length;i+=1){
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }
      for(int j=0;j<planets.length;j+=1){
        planets[j].update(dt,xForces[j],yForces[j]);
      }
      StdDraw.picture(0, 0, bgimageToDraw);
      for(int i = 0; i<planets.length;i+=1){
        planets[i].draw();
      }
      StdDraw.show();
      StdDraw.pause(20);
    }
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", scale);
    for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                      planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }

  }
}
