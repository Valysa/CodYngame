function array_sum(array, size){
    let sum = 0;
    for(let i = 0; i < size; i++){
        sum = sum + array[i];
    }
    return sum;
}

export { array_sum };