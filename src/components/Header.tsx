import React, { useState } from 'react';
import { Search, ChevronDown, Check, UserCheck, X } from 'lucide-react';
import { SECTIONS } from '../data/newsData';

interface HeaderProps {
  currentEdition: string;
  onSelectEdition: (edition: string) => void;
  onOpenSearch: () => void;
  onOpenSubscribe: () => void;
  onOpenLogin: () => void;
  onOpenTodaysPaper: () => void;
  isLoggedIn: boolean;
  userEmail?: string;
  onGoHome: () => void;
  activeSection?: string;
  onSelectSection: (section: string) => void;
  useWireframePhotos: boolean;
  onTogglePhotoStyle: () => void;
}

export const Header: React.FC<HeaderProps> = ({
  currentEdition,
  onSelectEdition,
  onOpenSearch,
  onOpenSubscribe,
  onOpenLogin,
  onOpenTodaysPaper,
  isLoggedIn,
  userEmail,
  onGoHome,
  activeSection,
  onSelectSection,
  useWireframePhotos,
  onTogglePhotoStyle,
}) => {
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
  const editions = ['U.S.', 'International', 'Canada', 'Español', '中文'];

  return (
    <header className="w-full max-w-[1340px] mx-auto px-4 pt-2 select-none">
      {/* Top Utility Bar */}
      <div className="flex items-center justify-between text-[11px] pb-2 text-[#333333] border-b border-nyt">
        {/* Left: Search & Editions */}
        <div className="flex items-center space-x-4">
          <button
            onClick={onOpenSearch}
            aria-label="Search"
            className="p-1 hover:text-black flex items-center gap-1.5 focus:outline-none transition-colors cursor-pointer group"
          >
            <Search className="w-3.5 h-3.5 text-gray-700 group-hover:text-black" />
            <span className="font-semibold uppercase tracking-wider text-[10px]">Search</span>
          </button>

          <nav aria-label="Editions" className="hidden md:flex items-center space-x-3 text-[#555] font-medium">
            {editions.map((ed, idx) => (
              <React.Fragment key={ed}>
                <button
                  onClick={() => onSelectEdition(ed)}
                  className={`text-[10px] tracking-wide transition-colors cursor-pointer ${
                    currentEdition === ed
                      ? 'font-bold text-black uppercase'
                      : 'hover:underline text-[#555]'
                  }`}
                >
                  {ed}
                </button>
                {idx < editions.length - 1 && <span className="text-gray-300">|</span>}
              </React.Fragment>
            ))}
          </nav>
        </div>

        {/* Right: Mode toggle, Account and Subscriptions */}
        <div className="flex items-center space-x-2.5">
          {/* Subtle Wireframe / Real photo toggle to match uploaded mockup vs high-res photos */}
          <button
            onClick={onTogglePhotoStyle}
            title="Toggle between authentic editorial photography and prompt wireframe placeholder graphic"
            className="hidden sm:inline-flex items-center gap-1 text-[10px] text-gray-500 hover:text-black px-2 py-1 rounded border border-gray-200 bg-gray-50 hover:bg-gray-100 transition-colors"
          >
            <span>Photo Mode:</span>
            <span className="font-semibold text-gray-800">{useWireframePhotos ? 'Wireframe Box' : 'Editorial'}</span>
          </button>

          {isLoggedIn ? (
            <div className="flex items-center gap-1.5 text-[11px] bg-gray-100 text-[#121212] px-2.5 py-1 rounded border border-gray-300">
              <UserCheck className="w-3.5 h-3.5 text-green-700" />
              <span className="font-semibold">{userEmail || 'Subscriber'}</span>
            </div>
          ) : (
            <>
              <button
                onClick={onOpenSubscribe}
                className="bg-[#567b95] hover:bg-[#326891] text-white font-bold text-[11px] px-3.5 py-1.5 rounded-sm uppercase tracking-wide transition-colors cursor-pointer shadow-xs active:translate-y-px"
              >
                Subscribe for $1/week
              </button>
              <button
                onClick={onOpenLogin}
                className="border border-[#121212] hover:bg-gray-50 text-black font-semibold text-[11px] px-3 py-1.5 rounded-sm transition-colors cursor-pointer active:translate-y-px"
              >
                Log In
              </button>
            </>
          )}
        </div>
      </div>

      {/* Middle Masthead Section */}
      <div className="pt-3 pb-2 flex flex-col items-center relative">
        <div className="w-full flex justify-between items-baseline text-[11px] text-[#333] mb-1">
          {/* Date & Today's Paper */}
          <div className="flex items-center gap-3">
            <span className="font-bold text-[#121212]">Wednesday, October 14, 2026</span>
            <button
              onClick={onOpenTodaysPaper}
              className="hidden sm:inline hover:underline text-[#666] cursor-pointer"
            >
              Today's Paper
            </button>
          </div>

          {/* Live Market index brief */}
          <div className="hidden sm:flex items-center gap-3 text-[11px] text-[#666]">
            <span className="inline-flex items-center gap-1">
              <strong className="text-black font-semibold">S&P 500</strong>{' '}
              <span className="text-emerald-700 font-medium">+0.42%</span>
            </span>
            <span className="inline-flex items-center gap-1">
              <strong className="text-black font-semibold">Nasdaq</strong>{' '}
              <span className="text-emerald-700 font-medium">+0.68%</span>
            </span>
          </div>
        </div>

        {/* Masthead Logo */}
        <button
          onClick={onGoHome}
          className="inline-block text-center select-none py-1 group cursor-pointer"
          title="The New York Times Home"
        >
          <h1 className="text-4xl sm:text-6xl md:text-7xl font-masthead tracking-tight text-[#121212] transition-opacity group-hover:opacity-90">
            The New York Times
          </h1>
        </button>
      </div>

      {/* Double Border Separator */}
      <div className="nyt-divider-double mb-1" />

      {/* Primary Sections Navigation */}
      <nav
        aria-label="Main Navigation"
        className="flex items-center justify-between overflow-x-auto no-scrollbar py-1 text-[11px] font-semibold tracking-tight text-[#121212] border-b border-nyt"
      >
        {SECTIONS.map((sec) => (
          <button
            key={sec}
            onClick={() => onSelectSection(sec)}
            className={`whitespace-nowrap px-2 py-0.5 transition-colors cursor-pointer ${
              activeSection === sec
                ? 'underline decoration-2 underline-offset-4 text-black font-bold'
                : 'hover:underline text-[#121212]'
            }`}
          >
            {sec}
          </button>
        ))}
      </nav>
      <div className="border-b-2 border-nyt mb-4" />
    </header>
  );
};
