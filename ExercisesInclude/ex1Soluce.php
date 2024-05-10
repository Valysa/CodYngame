<?php
    function arraySum($array, $size){
        $sum = 0;
        for($i = 0; $i < $size; $i++){
            $sum += $array[$i];
        }
        return $sum;
    }
?>