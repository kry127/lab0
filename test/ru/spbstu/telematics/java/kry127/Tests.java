package ru.spbstu.telematics.java.kry127;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.random.GaussianRandomGenerator;
import org.apache.commons.math3.random.NormalizedRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.RandomGeneratorFactory;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class Tests {

    final Random rnd = new Random(System.currentTimeMillis());

    @Test
    public void additionWithZeroMtrxGivesMatrix() {
        final int rows = 5;
        final int columns = 5;

        RealMatrix nil = new BlockRealMatrix(rows, columns); // нулевая матрица
        RandomGenerator rg = RandomGeneratorFactory.createRandomGenerator(rnd);
        GaussianRandomGenerator generator = new GaussianRandomGenerator(rg);

        for (int k = 1; k <= 3; k++) {
            double[][] arr = new double[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    arr[i][j] = generator.nextNormalizedDouble();
                }
            }
            RealMatrix a = new BlockRealMatrix(arr);

            assertEquals("Sum matrix with zero matrix, step " + k, a, Lab0.sumMatrix(a, nil));
        }
    }

    @Test
    public void additionScalarMatrix() {
        for (int k = 1; k <= 20; k++) {
            double f1 = rnd.nextFloat(); // установка float не даёт пройти тест
            double f2 = rnd.nextFloat(); // аналогично
            RealMatrix m1 = new BlockRealMatrix(new double[][] { new double[] {f1}});
            RealMatrix m2 = new BlockRealMatrix(new double[][] { new double[] {f2}});
            RealMatrix expect = new BlockRealMatrix(new double[][] { new double[] {f1 + f2}});
            assertEquals("Sum matrix with zero matrix, step " + k, expect, Lab0.sumMatrix(m1, m2));
        }
    }

    @Test
    public void sumOfBlockMatrix() {
        double[][] md1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        double[][] md2 = {{2, 3, 5}, {7, 11, 13}, {17, 19, 23}, {29, 31, 37}};
        double[][] md3 = new double[4][3];
        for (int i = 0; i < md1.length; i++) {
            for (int j = 0; j < md1[i].length; j++) {
                md3[i][j] = md1[i][j] + md2[i][j];
            }
        }

        RealMatrix m1 = new BlockRealMatrix(md1);
        RealMatrix m2 = new BlockRealMatrix(md2);
        RealMatrix res = new BlockRealMatrix(md3);

        assertEquals("Проверка суммы двух матриц", res, Lab0.sumMatrix(m1, m2));

    }
}
