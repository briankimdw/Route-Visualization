public class Gravity {
    public double falling(double time, double velo){
      double displacement = 0;

      displacement = (velo*time) + (.5*9.8*time*time);
      return displacement;
    }
}