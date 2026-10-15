import React, { useState, useEffect } from 'react';
import { X, RefreshCw, Award, HelpCircle } from 'lucide-react';

interface InteractiveWordleProps {
  initialGame?: string;
  onClose: () => void;
}

const TARGET_WORD = 'TIMES';
const MAX_ATTEMPTS = 6;

export const InteractiveWordle: React.FC<InteractiveWordleProps> = ({
  initialGame = 'Wordle',
  onClose,
}) => {
  const [activeTab, setActiveTab] = useState(initialGame);

  // Wordle State
  const [guesses, setGuesses] = useState<string[]>([]);
  const [currentGuess, setCurrentGuess] = useState<string>('');
  const [gameStatus, setGameStatus] = useState<'playing' | 'won' | 'lost'>('playing');
  const [message, setMessage] = useState<string>('Guess the 5-letter word of the day.');

  // Connections State
  const [selectedWords, setSelectedWords] = useState<string[]>([]);
  const [connectionsSolved, setConnectionsSolved] = useState<string[]>([]);

  // Mini State
  const [miniGrid, setMiniGrid] = useState<string[][]>([
    ['N', 'E', 'W', 'S', ''],
    ['Y', 'O', 'R', 'K', ''],
    ['P', 'O', 'S', 'T', ''],
    ['T', 'I', 'M', 'E', ''],
    ['', '', '', '', ''],
  ]);

  const handleKeyPress = (letter: string) => {
    if (gameStatus !== 'playing') return;
    if (currentGuess.length < 5) {
      setCurrentGuess((prev) => prev + letter.toUpperCase());
    }
  };

  const handleDelete = () => {
    if (gameStatus !== 'playing') return;
    setCurrentGuess((prev) => prev.slice(0, -1));
  };

  const handleEnter = () => {
    if (gameStatus !== 'playing') return;
    if (currentGuess.length !== 5) {
      setMessage('Word must be 5 letters long.');
      return;
    }

    const nextGuesses = [...guesses, currentGuess];
    setGuesses(nextGuesses);

    if (currentGuess === TARGET_WORD) {
      setGameStatus('won');
      setMessage('Splendid! You solved today’s Wordle No. 1,211!');
    } else if (nextGuesses.length >= MAX_ATTEMPTS) {
      setGameStatus('lost');
      setMessage(`Game over. Today’s word was ${TARGET_WORD}.`);
    } else {
      setMessage('Good try, keep going!');
    }
    setCurrentGuess('');
  };

  // Keyboard support
  useEffect(() => {
    const handleKeyDown = (e: KeyboardEvent) => {
      if (activeTab !== 'Wordle') return;
      if (e.key === 'Enter') {
        handleEnter();
      } else if (e.key === 'Backspace') {
        handleDelete();
      } else if (/^[a-zA-Z]$/.test(e.key)) {
        handleKeyPress(e.key);
      }
    };
    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [currentGuess, guesses, gameStatus, activeTab]);

  const getLetterStatus = (guess: string, index: number) => {
    const char = guess[index];
    if (char === TARGET_WORD[index]) return 'bg-[#6aaa64] text-white border-[#6aaa64]';
    if (TARGET_WORD.includes(char)) return 'bg-[#c9b458] text-white border-[#c9b458]';
    return 'bg-[#787c7e] text-white border-[#787c7e]';
  };

  const resetWordle = () => {
    setGuesses([]);
    setCurrentGuess('');
    setGameStatus('playing');
    setMessage('Guess the 5-letter word of the day.');
  };

  return (
    <div className="fixed inset-0 z-50 bg-black/60 backdrop-blur-xs flex items-center justify-center p-4">
      <div className="bg-white rounded-lg shadow-2xl w-full max-w-lg overflow-hidden border border-gray-300">
        {/* Game Header */}
        <div className="border-b border-nyt p-3 flex items-center justify-between bg-[#fbfbfb]">
          <div className="flex items-center gap-2">
            <span className="font-masthead text-xl text-black">NYT Games</span>
            <span className="text-gray-300">|</span>
            <span className="text-xs font-bold uppercase tracking-wider text-gray-700">
              {activeTab}
            </span>
          </div>

          <div className="flex items-center gap-1">
            <button
              onClick={onClose}
              className="p-1 hover:bg-gray-200 rounded text-gray-500 hover:text-black cursor-pointer"
            >
              <X className="w-5 h-5" />
            </button>
          </div>
        </div>

        {/* Tab Switcher */}
        <div className="flex border-b border-nyt text-xs font-semibold bg-gray-50">
          {(['Wordle', 'Connections', 'Spelling Bee', 'The Mini'] as const).map((tab) => (
            <button
              key={tab}
              onClick={() => setActiveTab(tab)}
              className={`flex-1 py-2 text-center transition-colors cursor-pointer ${
                activeTab === tab
                  ? 'border-b-2 border-black bg-white text-black font-bold'
                  : 'text-gray-600 hover:text-black'
              }`}
            >
              {tab}
            </button>
          ))}
        </div>

        {/* Game Area */}
        <div className="p-4 flex flex-col items-center justify-center min-h-[380px]">
          {activeTab === 'Wordle' && (
            <div className="w-full flex flex-col items-center">
              <div className="text-center mb-3">
                <h3 className="font-bold text-lg text-black">Wordle No. 1,211</h3>
                <p className="text-xs text-gray-600 mt-0.5">{message}</p>
              </div>

              {/* 6x5 Grid */}
              <div className="grid grid-rows-6 gap-1.5 mb-4">
                {Array.from({ length: MAX_ATTEMPTS }).map((_, rowIndex) => {
                  const guess = guesses[rowIndex];
                  const isCurrentRow = rowIndex === guesses.length;

                  return (
                    <div key={rowIndex} className="grid grid-cols-5 gap-1.5">
                      {Array.from({ length: 5 }).map((_, colIndex) => {
                        let letter = '';
                        let styleClass = 'border-2 border-gray-300 bg-white text-black';

                        if (guess) {
                          letter = guess[colIndex];
                          styleClass = getLetterStatus(guess, colIndex);
                        } else if (isCurrentRow) {
                          letter = currentGuess[colIndex] || '';
                          if (letter) styleClass = 'border-2 border-black bg-white text-black font-bold scale-105';
                        }

                        return (
                          <div
                            key={colIndex}
                            className={`w-10 h-10 sm:w-11 sm:h-11 flex items-center justify-center font-bold text-lg uppercase select-none rounded-xs transition-all ${styleClass}`}
                          >
                            {letter}
                          </div>
                        );
                      })}
                    </div>
                  );
                })}
              </div>

              {/* Virtual On-screen Keyboard */}
              <div className="w-full max-w-sm space-y-1">
                {['QWERTYUIOP', 'ASDFGHJKL', 'ZXCVBNM'].map((row, rowIdx) => (
                  <div key={rowIdx} className="flex justify-center gap-1">
                    {rowIdx === 2 && (
                      <button
                        onClick={handleEnter}
                        className="px-2 py-2 text-[10px] font-bold uppercase bg-gray-200 hover:bg-gray-300 rounded cursor-pointer"
                      >
                        Enter
                      </button>
                    )}
                    {row.split('').map((char) => (
                      <button
                        key={char}
                        onClick={() => handleKeyPress(char)}
                        className="w-7 h-9 sm:w-8 sm:h-9 bg-gray-200 hover:bg-gray-300 text-black font-bold text-xs rounded flex items-center justify-center cursor-pointer transition-colors"
                      >
                        {char}
                      </button>
                    ))}
                    {rowIdx === 2 && (
                      <button
                        onClick={handleDelete}
                        className="px-2 py-2 text-[10px] font-bold uppercase bg-gray-200 hover:bg-gray-300 rounded cursor-pointer"
                      >
                        Del
                      </button>
                    )}
                  </div>
                ))}
              </div>

              {gameStatus !== 'playing' && (
                <button
                  onClick={resetWordle}
                  className="mt-4 flex items-center gap-1.5 bg-black hover:bg-gray-800 text-white text-xs font-bold px-4 py-2 rounded cursor-pointer transition-colors"
                >
                  <RefreshCw className="w-3.5 h-3.5" />
                  <span>Play Again</span>
                </button>
              )}
            </div>
          )}

          {activeTab === 'Connections' && (
            <div className="text-center w-full max-w-md">
              <h3 className="font-bold text-base text-black mb-1">Connections</h3>
              <p className="text-xs text-gray-500 mb-4">Group words that share a common thread.</p>
              <div className="grid grid-cols-4 gap-2 mb-4">
                {[
                  'PAPER', 'TIMES', 'HEADLINE', 'COLUMN',
                  'BAROMETER', 'THERMOMETER', 'ANEMOMETER', 'RADAR',
                  'APPLE', 'ORANGE', 'PEACH', 'BERRY',
                  'PAWN', 'ROOK', 'BISHOP', 'KNIGHT'
                ].map((word) => (
                  <button
                    key={word}
                    onClick={() => {
                      if (selectedWords.includes(word)) {
                        setSelectedWords(selectedWords.filter(w => w !== word));
                      } else if (selectedWords.length < 4) {
                        setSelectedWords([...selectedWords, word]);
                      }
                    }}
                    className={`p-2.5 text-[11px] font-bold rounded uppercase tracking-wide transition-all cursor-pointer border ${
                      selectedWords.includes(word)
                        ? 'bg-black text-white border-black'
                        : 'bg-gray-100 hover:bg-gray-200 text-black border-transparent'
                    }`}
                  >
                    {word}
                  </button>
                ))}
              </div>
              <div className="text-xs text-gray-500">
                Selected: {selectedWords.length}/4
              </div>
            </div>
          )}

          {activeTab === 'Spelling Bee' && (
            <div className="text-center w-full max-w-sm">
              <h3 className="font-bold text-base text-black mb-1">Spelling Bee</h3>
              <p className="text-xs text-gray-500 mb-4">Make words using 7 letters (must include center letter).</p>
              <div className="relative w-44 h-44 mx-auto mb-4 flex items-center justify-center">
                <div className="w-14 h-14 bg-amber-400 rounded-full flex items-center justify-center font-bold text-xl text-black shadow-md z-10 cursor-pointer">
                  T
                </div>
                {['E', 'D', 'I', 'O', 'R', 'S'].map((letter, idx) => {
                  const angle = (idx * 60) * (Math.PI / 180);
                  const x = 54 * Math.cos(angle);
                  const y = 54 * Math.sin(angle);
                  return (
                    <div
                      key={letter}
                      style={{ transform: `translate(${x}px, ${y}px)` }}
                      className="absolute w-12 h-12 bg-gray-200 hover:bg-gray-300 rounded-full flex items-center justify-center font-bold text-base text-black transition-transform cursor-pointer"
                    >
                      {letter}
                    </div>
                  );
                })}
              </div>
              <div className="text-xs font-semibold text-gray-700 bg-amber-50 border border-amber-200 p-2 rounded">
                Rank: <span className="font-bold text-amber-900">Genius (42 pts)</span>
              </div>
            </div>
          )}

          {activeTab === 'The Mini' && (
            <div className="text-center">
              <h3 className="font-bold text-base text-black mb-1">The Mini Crossword</h3>
              <p className="text-xs text-gray-500 mb-3">Quick daily 5x5 solve.</p>
              <div className="grid grid-cols-4 gap-1 w-44 mx-auto mb-4">
                {['N', 'E', 'W', 'S', 'Y', 'O', 'R', 'K', 'T', 'I', 'M', 'E', 'P', 'A', 'P', 'R'].map((ch, idx) => (
                  <div
                    key={idx}
                    className="w-10 h-10 border border-gray-800 bg-white font-bold flex items-center justify-center text-sm"
                  >
                    {ch}
                  </div>
                ))}
              </div>
              <p className="text-xs text-green-700 font-bold">Solved in 0:34!</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};
