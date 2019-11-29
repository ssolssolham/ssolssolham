package main

import "fmt"

func rtn_tarzan(n int) {

	one := 10
	two := 20
	first := "타잔이"
	second := "원짜리 팬티를 입고,"
	third := "원짜리 칼을 차고 노래를 한다."

	for i := 0; i < n; i++ {
		fmt.Println(first, one, second, two, third)
		one += 10
		two += 10
	}

}

func main() {
	rtn_tarzan(4)
}
