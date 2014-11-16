package uk.ac.ed.inf.mandelbrotmaps.refactor.strategies;

import uk.ac.ed.inf.mandelbrotmaps.refactor.IFractalComputeDelegate;

public class MandelbrotCPUFractalComputeStrategy extends CPUFractalComputeStrategy {
    // Set the "maximum iteration" calculation constants
    // Empirically determined values for Mandelbrot set.
    public double getIterationBase() {
        return 1.24D;
    }

    public double getIterationConstantFactor() {
        return 54.0D;
    }

    @Override
    public void initialise(int width, int height, IFractalComputeDelegate delegate) {
        super.initialise(width, height, delegate);

//        Set home area
//        homeGraphArea = new MandelbrotJuliaLocation().getMandelbrotGraphArea();
    }

    int pixelInSet(int xPixel, int yPixel, int maxIterations) {
        boolean inside = true;
        int iterationNr;
        double newx, newy;
        double x, y;

        // Set x0 (real part of c)
        double x0 = xMin + ((double) xPixel * pixelSize);
        double y0 = yMax - ((double) yPixel * pixelSize); //TODO This shouldn't be calculated every time

        // Start at x0, y0
        x = x0;
        y = y0;

        //Run iterations over this point
        for (iterationNr = 0; iterationNr < maxIterations; iterationNr++) {
            // z^2 + c
            newx = (x * x) - (y * y) + x0;
            newy = (2 * x * y) + y0;

            x = newx;
            y = newy;

            // Well known result: if distance is >2, escapes to infinity...
            if ((x * x + y * y) > 4) {
                inside = false;
                break;
            }
        }

        if (inside)
            return this.colourStrategy.colourInsidePoint();
        else
            return this.colourStrategy.colourOutsidePoint(iterationNr, maxIterations);
    }

    @Override
    public boolean shouldPerformCrudeFirst() {
        return true;
    }
}
