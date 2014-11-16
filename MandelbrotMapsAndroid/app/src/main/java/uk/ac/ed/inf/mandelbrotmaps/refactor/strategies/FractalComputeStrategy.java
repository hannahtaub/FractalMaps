package uk.ac.ed.inf.mandelbrotmaps.refactor.strategies;

import uk.ac.ed.inf.mandelbrotmaps.colouring.IColourStrategy;
import uk.ac.ed.inf.mandelbrotmaps.refactor.IFractalComputeDelegate;

public abstract class FractalComputeStrategy implements IFractalComputeStrategy {
    protected int width;
    protected int height;
    protected IFractalComputeDelegate delegate;
    protected IColourStrategy colourStrategy;

    //protected double[] graphArea; // {x_min, y_max, width}
    //protected double[] homeGraphArea;

    // Render calculating variables
    double xMin, yMax, pixelSize;

    @Override
    public void initialise(int width, int height, IFractalComputeDelegate delegate) {
        this.width = width;
        this.height = height;
        this.delegate = delegate;
    }

    public void setColourStrategy(IColourStrategy colourStrategy) {
        this.colourStrategy = colourStrategy;
    }

    // Abstract methods

    abstract int pixelInSet(int xPixel, int yPixel, int maxIterations);
}
