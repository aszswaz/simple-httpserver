# Archlinux 打包
pkgname=httpserver
pkgver=v1.0.0
pkgrel=1
pkgdesc="简单的 HTTP 服务器"
arch=('x86_64')
depends=('jre11-openjdk>=11.0.15.u3-1')
makedepends=('maven>=3.8.4-1')
package() {
    cd ../
    mkdir -p "$pkgdir/usr/share/simple-httpserver" "$pkgdir/usr/bin"
    cp ./target/simple-httpserver.jar "$pkgdir/usr/share/simple-httpserver/simple-httpserver.jar"
    cp ./httpserver "$pkgdir/usr/bin/httpserver"
}
