import React, { useState } from 'react';
import { X, CheckCircle2, TrendingUp, Info } from 'lucide-react';
import { POLLING_DATA } from '../data/newsData';

interface PollingModalProps {
  initialState: string;
  onClose: () => void;
}

export const InteractivePollModal: React.FC<PollingModalProps> = ({
  initialState,
  onClose,
}) => {
  const [selectedState, setSelectedState] = useState(initialState);
  const currentPoll = POLLING_DATA[selectedState] || POLLING_DATA.PA;

  return (
    <div className="fixed inset-0 z-50 bg-black/60 backdrop-blur-xs flex items-center justify-center p-4">
      <div className="bg-white rounded-lg shadow-2xl w-full max-w-xl overflow-hidden border border-gray-300">
        <div className="border-b border-nyt p-3.5 flex items-center justify-between bg-gray-50">
          <div>
            <span className="text-[10px] uppercase font-bold tracking-wider text-red-700 block">
              The Upshot • 2026 Midterm Forecast
            </span>
            <h3 className="font-headline text-xl font-bold text-black">
              Key Battleground Senate Polling Averages
            </h3>
          </div>
          <button
            onClick={onClose}
            className="p-1 hover:bg-gray-200 rounded text-gray-500 hover:text-black cursor-pointer"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        <div className="p-5">
          {/* State Tabs */}
          <div className="flex gap-1.5 mb-6 overflow-x-auto pb-1">
            {Object.keys(POLLING_DATA).map((stateCode) => (
              <button
                key={stateCode}
                onClick={() => setSelectedState(stateCode)}
                className={`px-3 py-1.5 rounded text-xs font-bold transition-all cursor-pointer ${
                  selectedState === stateCode
                    ? 'bg-black text-white shadow-xs'
                    : 'bg-gray-100 text-gray-700 hover:bg-gray-200'
                }`}
              >
                {POLLING_DATA[stateCode].state}
              </button>
            ))}
          </div>

          {/* Active Poll Card */}
          <div className="bg-gray-50 border border-nyt p-4 rounded mb-6">
            <div className="flex justify-between items-baseline mb-2">
              <span className="font-headline text-lg font-bold text-black">
                {currentPoll.state} — {currentPoll.race}
              </span>
              <span
                className={`text-sm font-bold ${
                  currentPoll.leaderMargin.startsWith('Dem') ? 'text-blue-700' : 'text-red-700'
                }`}
              >
                {currentPoll.leaderMargin}
              </span>
            </div>

            {/* Split Bar */}
            <div className="w-full bg-gray-200 h-3.5 rounded-full overflow-hidden flex my-3">
              <div
                style={{ width: `${currentPoll.democrat.percentage}%` }}
                className="bg-blue-600 h-full flex items-center justify-center text-[10px] text-white font-bold"
              >
                {currentPoll.democrat.percentage.toFixed(1)}%
              </div>
              <div
                style={{ width: `${currentPoll.republican.percentage}%` }}
                className="bg-red-500 h-full flex items-center justify-center text-[10px] text-white font-bold"
              >
                {currentPoll.republican.percentage.toFixed(1)}%
              </div>
              <div
                style={{ width: `${currentPoll.undecided}%` }}
                className="bg-yellow-400 h-full"
              />
            </div>

            <div className="grid grid-cols-3 text-xs pt-2 border-t border-gray-200 text-gray-600">
              <div>
                <span className="font-semibold text-blue-700 block">Democratic nominee</span>
                <span>{currentPoll.democrat.percentage}%</span>
              </div>
              <div className="text-center">
                <span className="font-semibold text-gray-700 block">Undecided / 3rd Party</span>
                <span>{currentPoll.undecided}%</span>
              </div>
              <div className="text-right">
                <span className="font-semibold text-red-600 block">Republican nominee</span>
                <span>{currentPoll.republican.percentage}%</span>
              </div>
            </div>
          </div>

          <div className="text-xs text-gray-500 flex items-start gap-2 bg-blue-50/50 p-3 rounded border border-blue-100">
            <Info className="w-4 h-4 text-blue-600 shrink-0 mt-0.5" />
            <p>
              Averages reflect registered and likely voter polls conducted within the past 21 days, adjusted for pollster methodology, historical house effects, and sample size weighting.
            </p>
          </div>
        </div>

        <div className="p-3 bg-gray-50 border-t border-nyt flex justify-end">
          <button
            onClick={onClose}
            className="bg-black hover:bg-gray-800 text-white text-xs font-bold px-4 py-2 rounded cursor-pointer"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
};
