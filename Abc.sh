#!/bin/bash

current_day=$(date +%u)
current_date=$(date +%Y-%m-%d)

case $current_day in
    1) Start_date=$(date -d "$current_date -$(($(date +%u -d $current_date) + 2)) days" +%Y-%m-%d);;
    2) Start_date=$(date -d "$current_date +$(($(date +%u -d $current_date) + 1)) days" +%Y-%m-%d);;
    3) Start_date=$(date -d "$current_date -$(($(date +%u -d $current_date) + 2)) days" +%Y-%m-%d);;
    4|5) Start_date=$(date -d "$current_date -3 days" +%Y-%m-%d);;
    *) echo "Not handling the weekend (Saturday/Sunday) in this script.";;
esac

echo "Start_date: $Start_date"
