package com.pt.coremath

/**
 * Core mathematics utilities for PT Lifting Calculations
 */
class Math {
    companion object {
        /**
         * Calculate one rep max using Brzycki formula
         */
        fun oneRepMax(weight: Double, reps: Int): Double {
            return weight * (36.0 / (37.0 - reps))
        }
        
        /**
         * Calculate weight for target reps based on one rep max
         */
        fun weightForReps(oneRepMax: Double, targetReps: Int): Double {
            return oneRepMax * (37.0 - targetReps) / 36.0
        }
        
        /**
         * Calculate percentage of one rep max
         */
        fun percentageOfMax(weight: Double, oneRepMax: Double): Double {
            return (weight / oneRepMax) * 100.0
        }
        
        /**
         * Round weight to nearest standard plate increment
         */
        fun roundToPlateIncrement(weight: Double, increment: Double = 2.5): Double {
            return kotlin.math.round(weight / increment) * increment
        }
    }
}
