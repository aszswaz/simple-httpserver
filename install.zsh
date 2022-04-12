#!/bin/env zsh

mvn clean package &&
    makepkg -f &&
    sudo pacman -U httpserver-*.pkg.tar.zst
