package main

import "fmt"

func facItr2(n int) int{
	result := 1
	for i := 2; i <= n; i++{
		result *= i
	}
	return result
}

func facItr(n int) int {
	result := 1
	for n > 0 {
		result *= n
		n--
	}
	return result
}

func main(){
	fmt.Println(facItr(5))
}