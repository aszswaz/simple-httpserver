package utils

import (
	"fmt"
	"os"
)

//goland:noinspection GoUnhandledErrorResult
func PrintErr(e error) {
	fmt.Fprint(os.Stderr, "\033[91m")
	fmt.Fprintln(os.Stderr, e)
	fmt.Fprint(os.Stderr, "\033[0m")
}
