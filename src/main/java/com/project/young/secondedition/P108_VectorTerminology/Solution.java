package com.project.young.secondedition.P108_VectorTerminology;

import jdk.incubator.vector.*;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteOrder;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        VectorSpecies<Double> VS1 = VectorSpecies.of(double.class, VectorShape.S_512_BIT);
        VectorSpecies<Double> VS2 = VectorSpecies.ofLargestShape(double.class);
        VectorSpecies<Double> VS3 = DoubleVector.SPECIES_512;
        VectorSpecies<Double> VS4 = DoubleVector.SPECIES_PREFERRED;

        System.out.println(VS1.elementType());
        System.out.println(VS1.vectorShape());
        System.out.println(VS2.elementType());
        System.out.println(VS2.vectorShape());
        System.out.println(VS3.elementType());
        System.out.println(VS3.vectorShape());
        System.out.println(VS4.elementType());
        System.out.println(VS4.vectorShape());

        // getting length (number of lanes)
        VectorSpecies<Float> VSF = FloatVector.SPECIES_256;
        System.out.println(VSF.length()); // 8
        System.out.println(VSF.vectorBitSize() / VSF.elementSize()); // 8
        System.out.println();

        VectorSpecies<Integer> VS256 = IntVector.SPECIES_256;
        Vector<Integer> v1 = VS256.zero();
        System.out.println(Arrays.toString(v1.toIntArray()));
        Vector<Integer> v2 = VS256.zero();
        System.out.println(Arrays.toString(v2.toIntArray()));
        Vector<Integer> v3 = VS256.broadcast(5);
        System.out.println(Arrays.toString(v3.toIntArray()));
        IntVector v4 = IntVector.broadcast(VS256, 5);
        System.out.println(Arrays.toString(v4.toIntArray()));

        // fromArray()
        int[] varr1 = new int[] {0, 1, 2, 3, 4, 5, 6, 7};
        Vector<Integer> vfa1 = VS256.fromArray(varr1, 0);
        System.out.println(Arrays.toString(vfa1.toIntArray()));

        // fromMemorySegment()
        IntVector vfms;
        MemorySegment segment;
        try (Arena arena = Arena.ofConfined()) {

            segment = arena.allocate(32);
            segment.setAtIndex(ValueLayout.JAVA_INT, 0, 11);
            segment.setAtIndex(ValueLayout.JAVA_INT, 1, 21);
            segment.setAtIndex(ValueLayout.JAVA_INT, 2, 12);
            segment.setAtIndex(ValueLayout.JAVA_INT, 3, 7);
            segment.setAtIndex(ValueLayout.JAVA_INT, 4, 33);
            segment.setAtIndex(ValueLayout.JAVA_INT, 5, 1);
            segment.setAtIndex(ValueLayout.JAVA_INT, 6, 3);
            segment.setAtIndex(ValueLayout.JAVA_INT, 7, 6);

            vfms = IntVector.fromMemorySegment(VS256, segment, 0, ByteOrder.nativeOrder());
        }

        System.out.println(Arrays.toString(vfms.toIntArray()));

        // slice
        int[] v = new int[] {10, 11, 12, 13, 14, 15, 16, 17};
        Vector<Integer> vo = VS256.fromArray(v, 0);
        Vector<Integer> vs = vo.slice(vo.length()/2);
        System.out.println("Original vector: " + Arrays.toString(vo.toIntArray()));
        System.out.println("Sliced vector: " + Arrays.toString(vs.toIntArray()));

        // expand and compress
        VectorMask<Integer> vm = VectorMask.fromArray(VS256,
                new boolean[]{false, false, true, false, false, true, true, false}, 0);
        Vector<Integer> ve = vo.expand(vm);
        Vector<Integer> vc = vo.compress(vm);
        System.out.println("Expanded vector: " + Arrays.toString(ve.toIntArray()));
        System.out.println("Compressed vector: " + Arrays.toString(vc.toIntArray()));

        // cast shape
        VectorSpecies<Integer> VS512cast = IntVector.SPECIES_512;
        VectorSpecies<Integer> VS256cast = IntVector.SPECIES_256;
        VectorSpecies<Integer> VS128cast = IntVector.SPECIES_128;
        IntVector vs1cast = IntVector.fromArray(VS256cast, new int[] {0, 1, 2, 3, 4, 5, 6, 7}, 0);
        Vector<Integer> vs0cast = vs1cast.castShape(VS512cast, 0);
        Vector<Integer> vs2cast = vs1cast.castShape(VS128cast, 0);
        System.out.println("vs0cast: " + Arrays.toString(vs0cast.toIntArray()));
        System.out.println("vs1cast: " + Arrays.toString(vs1cast.toIntArray()));
        System.out.println("vs2cast: " + Arrays.toString(vs2cast.toIntArray()));
    }
}
