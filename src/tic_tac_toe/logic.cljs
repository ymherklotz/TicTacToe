(ns tic-tac-toe.logic)

;; Define global variables
(def board-size 3)

;; Functions
(defn new-board
  [size]
  (vec (repeat size (vec (repeat size 0)))))
