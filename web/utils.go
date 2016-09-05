package web

import (
	"fmt"
	"math/rand"
	"os"
	"os/exec"
	"syscall"

	"github.com/spf13/viper"
)

//IsProduction production mode ?
func IsProduction() bool {
	return viper.GetString("env") == "production"
}

//Host hostname
func Host() string {
	if IsProduction() {
		if viper.GetBool("http.ssl") {
			return fmt.Sprintf("https://%s", viper.GetString("http.domain"))
		}
		return fmt.Sprintf("http://%s", viper.GetString("http.domain"))
	}
	return fmt.Sprintf("http://localhost:%d", viper.GetInt("http.port"))
}

//RandomStr random string
func RandomStr(n int) string {
	letters := []rune("abcdefghijklmnopqrstuvwxyz0123456789")
	buf := make([]rune, n)
	for i := range buf {
		buf[i] = letters[rand.Intn(len(letters))]
	}
	return string(buf)
}

//Shell run shell command
func Shell(cmd string, args ...string) error {
	bin, err := exec.LookPath(cmd)
	if err != nil {
		return err
	}
	return syscall.Exec(bin, append([]string{cmd}, args...), os.Environ())
}

func init() {

	viper.SetDefault("http", map[string]interface{}{
		"host": "www.change-me.com",
		"port": 8080,
		"ssl":  false,
	})

	viper.SetDefault("secrets", RandomStr(512))
}
