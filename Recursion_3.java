public class Main {


    private static double func(double x) {
        return Math.cos(Math.pow(x, 5) * Math.PI / 180) + Math.pow(x, 4) - 345.3 * x - 23;
    }

    private static double halfSection(double a, double b, double accurate) {
        var x = (a + b) / 2;
        if (func(a) * func(x) < 0)
            b = x;
        else if (func(x) * func(b) < 0)
            a = x;
        if (Math.abs(b - a) > 2 * accurate) return halfSection(a, b, accurate);
        return (a + b) / 2;
    }


    public static void main(String[] args) {
        System.out.println(halfSection(0, 10, 0.001));
    }
}