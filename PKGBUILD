# This is an example PKGBUILD file. Use this as a start to creating your own,
# and remove these comments. For more information, see 'man PKGBUILD'.
# NOTE: Please fill out the license field for your package! If it is unknown,
# then please put 'unknown'.

# Maintainer: Michael Zappa  <zapprich@gmail.com>
pkgname=minesweeper-zappa-git
pkgver=1.0
pkgrel=1
pkgdesc="Mostly follows the rules of minesweeper"
arch=(x86_64)
url="https://github/michzappa/minesweeper.git"
license=('unknown')
depends=(jre-openjdk)
makedepends=(git jdk-openjdk)
provides=(minesweeper)
source=("git+$url")
md5sums=('SKIP')

build() {
	cd "$pkgname"
	./configure --prefix=/usr
	make
}

package() {
	make DESTDIR="$pkgdir/" install
}
